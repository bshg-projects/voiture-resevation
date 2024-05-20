package com.example.voitureresevation.zsecurity.permissions;

import com.example.voitureresevation.zsecurity.entity.Permission;
import com.example.voitureresevation.zsecurity.entity.Role;
import com.example.voitureresevation.zsecurity.permissions.resources.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum RoleEnum {
    ADMIN(
            UserPermission.values(),
            RolePermission.values(),
            ContratPermission.values(),
            AdministrateurPermission.values(),
            ClientPermission.values(),
            ReservationPermission.values(),
            OffrePermission.values(),
            VoiturePermission.values()
    ),
    ;
    private Set<PermissionResource> permissions = null;

    RoleEnum() {
    }

    RoleEnum(Object... permissionsList) {
        this.permissions = Arrays.stream(permissionsList)
                .flatMap(o -> {
                    if (o instanceof PermissionResource[]) {
                        return Arrays.stream((PermissionResource[]) o);
                    } else if (o instanceof PermissionResource) {
                        return Arrays.stream(new PermissionResource[]{(PermissionResource) o});
                    } else {
                        throw new IllegalArgumentException("Unsupported type in permissionsList");
                    }
                }).collect(Collectors.toUnmodifiableSet());
    }

    public Set<PermissionResource> getPermissions() {
        return permissions;
    }

    public Role getRole() {
        var result = new Role();
        result.setName(this.name());
        result.setPermissions(
                this.getPermissions().stream()
                        .map(p -> new Permission(p.authority()))
                        .collect(Collectors.toSet())
        );
        return result;
    }
}