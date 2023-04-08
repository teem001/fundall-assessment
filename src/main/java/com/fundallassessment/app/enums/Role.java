package com.fundallassessment.app.enums;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.fundallassessment.app.enums.Permission.*;


public enum Role {
    SUPER_ADMIN(Set.of(SUPER_ADMIN_READ,SUPER_ADMIN_DELETE,SUPER_ADMIN_CREATE,SUPER_ADMIN_WRITE)),

    ADMIN(Set.of(ADMIN_READ,ADMIN_DELETE,ADMIN_CREATE,ADMIN_WRITE)),

    USER(Set.of(Permission.USER));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    //TODO grant the each roles with the authorities in their respective sets
    public Set<GrantedAuthority> grantedAuthorities(){
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return permissions;

    }

}

