package com.example.voitureresevation.zsecurity.service.impl;

import com.example.voitureresevation.zsecurity.dao.PermissionDao;
import com.example.voitureresevation.zsecurity.entity.Permission;
import com.example.voitureresevation.zsecurity.service.facade.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public Permission save(Permission permission) {
        Permission perm = permissionDao.findByName(permission.getName());
        return perm == null ? permissionDao.save(permission) : perm;
    }
}