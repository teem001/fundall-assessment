package com.fundallassessment.app.enums;

public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),
    SUPER_ADMIN_READ("admin:read"),
    SUPER_ADMIN_WRITE("admin:write"),
    SUPER_ADMIN_DELETE("admin:delete"),
    SUPER_ADMIN_CREATE("admin:create"),
    USER("user:read");




    private final String permission;




    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}


