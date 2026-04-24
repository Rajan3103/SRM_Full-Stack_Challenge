package com.karthigrajan.bfhl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HierarchyObject {
    private String root;
    private Map<String, Object> tree;
    private Integer depth;
    private Boolean has_cycle;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Map<String, Object> getTree() {
        return tree;
    }

    public void setTree(Map<String, Object> tree) {
        this.tree = tree;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Boolean getHas_cycle() {
        return has_cycle;
    }

    public void setHas_cycle(Boolean has_cycle) {
        this.has_cycle = has_cycle;
    }
}
