package com.istar.service.dto.Administrator.UsersManagement;

import java.util.List;

public class UserInfoDTO {
    private Long id;
    private String username;
    private String token;
    private String role;
    private List<FeaturePermissionDTO> permissions;

    public UserInfoDTO() {}

    public UserInfoDTO(Long id, String username, String token, String role, List<FeaturePermissionDTO> permissions) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.role = role;
        this.permissions = permissions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<FeaturePermissionDTO> getPermissions() { return permissions; }
    public void setPermissions(List<FeaturePermissionDTO> permissions) { this.permissions = permissions; }
}
