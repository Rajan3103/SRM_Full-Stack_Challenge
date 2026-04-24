package com.karthigrajan.bfhl.model;

import java.util.List;

public class BfhlResponse {
    private String user_id = "karthig_Rajan_S_31032005";      
    private String email_id = "kr4505@srmist.edu.in";    
    private String college_roll_number = "RA2311003050204"; 
    private List<HierarchyObject> hierarchies;
    private List<String> invalid_entries;
    private List<String> duplicate_edges;
    private SummaryObject summary;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getCollege_roll_number() {
        return college_roll_number;
    }

    public void setCollege_roll_number(String college_roll_number) {
        this.college_roll_number = college_roll_number;
    }

    public List<HierarchyObject> getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(List<HierarchyObject> hierarchies) {
        this.hierarchies = hierarchies;
    }

    public List<String> getInvalid_entries() {
        return invalid_entries;
    }

    public void setInvalid_entries(List<String> invalid_entries) {
        this.invalid_entries = invalid_entries;
    }

    public List<String> getDuplicate_edges() {
        return duplicate_edges;
    }

    public void setDuplicate_edges(List<String> duplicate_edges) {
        this.duplicate_edges = duplicate_edges;
    }

    public SummaryObject getSummary() {
        return summary;
    }

    public void setSummary(SummaryObject summary) {
        this.summary = summary;
    }
}
