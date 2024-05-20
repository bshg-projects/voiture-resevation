package com.example.voitureresevation.zsecurity.ws.dto;

import com.example.voitureresevation.zutils.webservice.dto.AuditBaseDto;

import java.util.ArrayList;
import java.util.List;

public class PermissionDto extends AuditBaseDto {
    private String name;

    List<RoleDto> roles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
