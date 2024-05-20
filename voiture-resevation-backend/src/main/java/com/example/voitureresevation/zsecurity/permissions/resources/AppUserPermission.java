package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum AppUserPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    AppUserPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "appUser";
    }

    @Override
    public String authority() {
        return value;
    }
}
