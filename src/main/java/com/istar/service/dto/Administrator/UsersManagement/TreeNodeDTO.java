package com.istar.service.dto.Administrator.UsersManagement;
// TreeNodeDTO.java
import java.util.ArrayList;
import java.util.List;

public class TreeNodeDTO {
    private String key;
    private boolean selectable = true;
    private TreeNodeData data;
    private List<TreeNodeDTO> children = new ArrayList<>();

    // Getters and Setters
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public boolean isSelectable() {
        return selectable;
    }
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
    public TreeNodeData getData() {
        return data;
    }
    public void setData(TreeNodeData data) {
        this.data = data;
    }
    public List<TreeNodeDTO> getChildren() {
        return children;
    }
    public void setChildren(List<TreeNodeDTO> children) {
        this.children = children;
    }

    public static class TreeNodeData {
        private String name;
        private String description;

        // Getters and Setters
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }
}
