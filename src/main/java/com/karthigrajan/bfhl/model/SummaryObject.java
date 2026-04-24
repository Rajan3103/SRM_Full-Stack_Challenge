package com.karthigrajan.bfhl.model;

public class SummaryObject {
    private Integer total_trees;
    private Integer total_cycles;
    private String largest_tree_root;

    public SummaryObject() {}

    public SummaryObject(Integer total_trees, Integer total_cycles, String largest_tree_root) {
        this.total_trees = total_trees;
        this.total_cycles = total_cycles;
        this.largest_tree_root = largest_tree_root;
    }

    public Integer getTotal_trees() {
        return total_trees;
    }

    public void setTotal_trees(Integer total_trees) {
        this.total_trees = total_trees;
    }

    public Integer getTotal_cycles() {
        return total_cycles;
    }

    public void setTotal_cycles(Integer total_cycles) {
        this.total_cycles = total_cycles;
    }

    public String getLargest_tree_root() {
        return largest_tree_root;
    }

    public void setLargest_tree_root(String largest_tree_root) {
        this.largest_tree_root = largest_tree_root;
    }
}
