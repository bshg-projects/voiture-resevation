package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum OffrePermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    OffrePermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "offre";
    }

    @Override
    public String authority() {
        return value;
    }
}
