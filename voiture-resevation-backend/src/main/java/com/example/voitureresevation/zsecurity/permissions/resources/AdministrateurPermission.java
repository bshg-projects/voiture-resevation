package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum AdministrateurPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    AdministrateurPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "administrateur";
    }

    @Override
    public String authority() {
        return value;
    }
}
