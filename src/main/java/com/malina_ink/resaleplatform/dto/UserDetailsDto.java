package com.malina_ink.resaleplatform.dto;

import com.malina_ink.resaleplatform.enums.Role;

public class UserDetailsDto {
    private final String username;
    private final String password;
    private final int userId;
    private final Role role;
    private final String name;

    public UserDetailsDto(String username, String password, int userId, Role role, String name) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.role = role;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
