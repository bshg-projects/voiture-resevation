package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum ContratPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    ContratPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "contrat";
    }

    @Override
    public String authority() {
        return value;
    }
}
