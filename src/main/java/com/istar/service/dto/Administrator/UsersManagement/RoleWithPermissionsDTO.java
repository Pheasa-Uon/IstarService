package com.istar.service.dto.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Role;
import com.istar.service.Entity.Administrator.UsersManagment.RoleFeaturePermission;
import lombok.Data;
import java.util.List;

@Data
public class RoleWithPermissionsDTO {
    private Role role;
    private List<RoleFeaturePermission> permissions;


}
