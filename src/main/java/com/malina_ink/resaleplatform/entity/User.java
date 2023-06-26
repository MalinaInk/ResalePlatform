package com.malina_ink.resaleplatform.entity;

import com.malina_ink.resaleplatform.enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String username;
    private String password;
    private String firstname;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate regDate;
    private String city;
    private String image;
    private Role role;

}
