package com.karthigrajan.bfhl.service;

import com.karthigrajan.bfhl.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BfhlService {

    private static final Pattern EDGE_PATTERN = Pattern.compile("^[A-Z]->[A-Z]$");

    public BfhlResponse processData(BfhlRequest request) {
        List<String> rawData = request.getData() != null ? request.getData() : Collections.emptyList();
        List<String> invalidEntries = new ArrayList<>();
        List<String> duplicateEdges = new ArrayList<>();
        Set<String> uniqueEdges = new LinkedHashSet<>();
        
        // Step 1 & 2: Validate and Duplicate Detection
        for (String entry : rawData) {
            String trimmed = entry.trim();
            if (!isValid(trimmed)) {
                invalidEntries.add(entry);
                continue;
            }
            if (!uniqueEdges.add(trimmed)) {
                if (!duplicateEdges.contains(trimmed)) {
                    duplicateEdges.add(trimmed);
                }
            }
        }

        // Step 3: Build Adjacency List & Parent Mapping
        Map<String, List<String>> adj = new TreeMap<>(); 
        Map<String, String> parentOf = new HashMap<>();
        Set<String> allNodes = new TreeSet<>();

        for (String edge : uniqueEdges) {
            String[] parts = edge.split("->");
            String parent = parts[0];
            String child = parts[1];
            
            allNodes.add(parent);
            allNodes.add(child);

            if (!parentOf.containsKey(child)) {
                parentOf.put(child, parent);
                adj.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            }
        }

        // Identify Roots and Cycles
        List<HierarchyObject> hierarchies = new ArrayList<>();
        Set<String> processedNodes = new HashSet<>();
        
        List<String> roots = allNodes.stream()
                .filter(node -> !parentOf.containsKey(node))
                .sorted()
                .collect(Collectors.toList());

        // Process trees starting from roots
        for (String root : roots) {
            if (!processedNodes.contains(root)) {
                HierarchyObject ho = processGroup(root, adj, processedNodes);
                hierarchies.add(ho);
            }
        }

        // Process pure cycles
        List<String> remainingNodes = allNodes.stream()
                .filter(n -> !processedNodes.contains(n))
                .collect(Collectors.toList());
        
        while (!remainingNodes.isEmpty()) {
            String startNode = remainingNodes.get(0);
            Set<String> component = new HashSet<>();
            findComponent(startNode, adj, parentOf, component);
            
            String cycleRoot = component.stream().min(String::compareTo).orElse(startNode);
            HierarchyObject ho = new HierarchyObject();
            ho.setRoot(cycleRoot);
            ho.setTree(new HashMap<>());
            ho.setHas_cycle(true);
            hierarchies.add(ho);
            
            processedNodes.addAll(component);
            remainingNodes.removeIf(processedNodes::contains);
        }

        // Summary
        int totalTrees = 0;
        int totalCycles = 0;
        String largestTreeRoot = null;
        int maxDepth = -1;

        for (HierarchyObject ho : hierarchies) {
            if (ho.getHas_cycle() != null && ho.getHas_cycle()) {
                totalCycles++;
            } else {
                totalTrees++;
                if (ho.getDepth() > maxDepth) {
                    maxDepth = ho.getDepth();
                    largestTreeRoot = ho.getRoot();
                } else if (ho.getDepth() == maxDepth) {
                    if (largestTreeRoot == null || ho.getRoot().compareTo(largestTreeRoot) < 0) {
                        largestTreeRoot = ho.getRoot();
                    }
                }
            }
        }

        BfhlResponse response = new BfhlResponse();
        response.setHierarchies(hierarchies);
        response.setInvalid_entries(invalidEntries);
        response.setDuplicate_edges(duplicateEdges);
        response.setSummary(new SummaryObject(totalTrees, totalCycles, largestTreeRoot));

        return response;
    }

    private boolean isValid(String entry) {
        if (entry.isEmpty() || !EDGE_PATTERN.matcher(entry).matches()) return false;
        String[] parts = entry.split("->");
        return !parts[0].equals(parts[1]); 
    }

    private HierarchyObject processGroup(String root, Map<String, List<String>> adj, Set<String> processedNodes) {
        HierarchyObject ho = new HierarchyObject();
        ho.setRoot(root);
        
        Set<String> recursionStack = new HashSet<>();
        Set<String> visitedInThisDFS = new HashSet<>();
        boolean hasCycle = detectCycle(root, adj, visitedInThisDFS, recursionStack);
        
        if (hasCycle) {
            ho.setTree(new HashMap<>());
            ho.setHas_cycle(true);
            collectReachable(root, adj, processedNodes);
        } else {
            Map<String, Object> tree = new HashMap<>();
            tree.put(root, buildNestedTree(root, adj, processedNodes));
            ho.setTree(tree);
            ho.setDepth(calculateDepth(root, adj));
        }
        
        return ho;
    }

    private boolean detectCycle(String node, Map<String, List<String>> adj, Set<String> visited, Set<String> recStack) {
        if (recStack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        recStack.add(node);

        List<String> children = adj.getOrDefault(node, Collections.emptyList());
        for (String child : children) {
            if (detectCycle(child, adj, visited, recStack)) return true;
        }

        recStack.remove(node);
        return false;
    }

    private Map<String, Object> buildNestedTree(String node, Map<String, List<String>> adj, Set<String> processedNodes) {
        processedNodes.add(node);
        Map<String, Object> childrenMap = new TreeMap<>();
        List<String> children = adj.getOrDefault(node, Collections.emptyList());
        for (String child : children) {
            childrenMap.put(child, buildNestedTree(child, adj, processedNodes));
        }
        return childrenMap;
    }

    private int calculateDepth(String node, Map<String, List<String>> adj) {
        List<String> children = adj.getOrDefault(node, Collections.emptyList());
        if (children.isEmpty()) return 1;
        int maxChildDepth = 0;
        for (String child : children) {
            maxChildDepth = Math.max(maxChildDepth, calculateDepth(child, adj));
        }
        return 1 + maxChildDepth;
    }

    private void collectReachable(String node, Map<String, List<String>> adj, Set<String> visited) {
        if (visited.contains(node)) return;
        visited.add(node);
        for (String child : adj.getOrDefault(node, Collections.emptyList())) {
            collectReachable(child, adj, visited);
        }
    }

    private void findComponent(String node, Map<String, List<String>> adj, Map<String, String> parentOf, Set<String> component) {
        if (component.contains(node)) return;
        component.add(node);
        for (String child : adj.getOrDefault(node, Collections.emptyList())) {
            findComponent(child, adj, parentOf, component);
        }
        String parent = parentOf.get(node);
        if (parent != null) {
            findComponent(parent, adj, parentOf, component);
        }
    }
}
