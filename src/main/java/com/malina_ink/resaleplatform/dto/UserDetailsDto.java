package com.malina_ink.resaleplatform.dto;

import com.malina_ink.resaleplatform.enums.Role;
import lombok.Data;

public class UserDetailsDto {
    private final String username;
    private final String password;
    private final int userId;
    private final Role role;

    public UserDetailsDto(String username, String password, int userId, Role role) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.role = role;
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
}
