package com.doublevpartners.ticketmanagement.model.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum RoleEnum {
    USER (new SimpleGrantedAuthority("USER")),
    SUPPORT(new SimpleGrantedAuthority("SUPPORT")),
    DISABLED(new SimpleGrantedAuthority("DISABLED")),
    ADMIN(new SimpleGrantedAuthority("ADMIN")),
    ;

    private final SimpleGrantedAuthority authority;
    RoleEnum(SimpleGrantedAuthority authority) {
        this.authority = authority;
    }

    public static RoleEnum fromString(String role) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.authority.getAuthority().equalsIgnoreCase(role)) {
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Role: " + role);
    }

}
