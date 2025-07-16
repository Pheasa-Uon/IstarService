package com.istar.service.dto.Administrator.UsersManagement;

import java.util.ArrayList;
import java.util.List;

public class FeatureTreeDTO {

    private Long id;
    private String name;
    private String code;
    private String type;
    private String routePath;
    private String icon;
    private Boolean bStatus;
    private Integer order;
    private List<FeatureTreeDTO> children = new ArrayList<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getBStatus() {
        return bStatus;
    }

    public void setBStatus(Boolean bStatus) {
        this.bStatus = bStatus;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<FeatureTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<FeatureTreeDTO> children) {
        this.children = children;
    }
}
