package com.example.Springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
public class UserDetailsDto {
    private int empId;
    private String name;
    private String email;
    private String password;
    private String username;
    private Set<String>roleNames;

    public UserDetailsDto(int empId, String name, String email, String password, String username, Set<String> roleNames) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roleNames = roleNames;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }
}

