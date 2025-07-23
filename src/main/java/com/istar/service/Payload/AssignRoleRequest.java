package com.istar.service.Payload;

import lombok.Data;

@Data
public class AssignRoleRequest {
    private Long userId;
    private Long roleId;
}
