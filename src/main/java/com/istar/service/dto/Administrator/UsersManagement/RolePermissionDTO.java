package com.istar.service.dto.Administrator.UsersManagement;

import java.util.List;

public class RolePermissionDTO {

    private Long roleId;
    private List<Long> featureIds;

    // Constructors
    public RolePermissionDTO() {}

    public RolePermissionDTO(Long roleId, List<Long> featureIds) {
        this.roleId = roleId;
        this.featureIds = featureIds;
    }

    // Getters and Setters
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getFeatureIds() {
        return featureIds;
    }

    public void setFeatureIds(List<Long> featureIds) {
        this.featureIds = featureIds;
    }
}
