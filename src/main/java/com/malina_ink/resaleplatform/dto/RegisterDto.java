package com.malina_ink.resaleplatform.dto;

import com.malina_ink.resaleplatform.enums.Role;
import lombok.Data;

import java.util.Objects;
@Data
public class RegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public RegisterDto(String username,
                       String password,
                       String firstName,
                       String lastName,
                       String phone,
                       Role role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDto that = (RegisterDto) o;
        return username.equals(that.username) && password.equals(that.password) && firstName.equals(that.firstName) && lastName.equals(that.lastName) && phone.equals(that.phone) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, phone, role);
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
