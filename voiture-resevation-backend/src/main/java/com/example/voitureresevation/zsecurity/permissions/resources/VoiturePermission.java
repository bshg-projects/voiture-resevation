package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum VoiturePermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    VoiturePermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "voiture";
    }

    @Override
    public String authority() {
        return value;
    }
}
