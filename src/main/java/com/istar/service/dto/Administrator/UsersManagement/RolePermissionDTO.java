package com.istar.service.dto.Administrator.UsersManagement;

import java.util.List;

public class RoleDto {
    private String name;
    private String description;
    private String rolesCode;
    private String rolesStatus;
    private List<Long> featureIds; // IDs of selected features

    // Getters and setters
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

    public String getRolesCode() {
        return rolesCode;
    }
    public void setRolesCode(String rolesCode) {
        this.rolesCode = rolesCode;
    }

    public String getRolesStatus() {
        return rolesStatus;
    }
    public void setRolesStatus(String rolesStatus) {
        this.rolesStatus = rolesStatus;
    }

    public List<Long> getFeatureIds() {
        return featureIds;
    }
    public void setFeatureIds(List<Long> featureIds) {
        this.featureIds = featureIds;
    }
}