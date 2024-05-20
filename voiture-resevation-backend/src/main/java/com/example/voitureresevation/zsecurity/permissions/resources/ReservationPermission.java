package com.example.voitureresevation.zsecurity.permissions.resources;

import com.example.voitureresevation.zsecurity.permissions.PermissionResource;

public enum ReservationPermission implements PermissionResource {
    CREATE(create),
    READ(read),
    EDIT(edit),
    DELETE(delete),
    ;

    private final String value;

    ReservationPermission(String action) {
        this.value = authority(action);
    }

    @Override
    public String resource() {
        return "reservation";
    }

    @Override
    public String authority() {
        return value;
    }
}
