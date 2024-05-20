package com.example.voitureresevation.zsecurity.ws.restapi;

import com.example.voitureresevation.zsecurity.service.facade.RoleService;
import com.example.voitureresevation.zsecurity.ws.converter.RoleConverter;
import com.example.voitureresevation.zsecurity.ws.dto.RoleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/app-roles")
public class RoleRest {
    private final RoleService roleService;
    private final RoleConverter roleConverter;

    public RoleRest(RoleService roleService, RoleConverter roleConverter) {
        this.roleService = roleService;
        this.roleConverter = roleConverter;
    }

    @GetMapping("/")
    public List<RoleDto> findAll() {
        return roleConverter.toDto(this.roleService.findAll());
    }
}
