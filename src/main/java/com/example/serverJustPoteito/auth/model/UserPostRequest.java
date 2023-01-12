package com.example.serverJustPoteito.auth.model;

import com.example.serverJustPoteito.auth.persistence.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserPostRequest {

    private Integer id;
    private String name;
    private String surnames;
    private String userName;
    private String email;
    private String password;
    private boolean isEnabled;
    private Set<Role> roles;

    public UserPostRequest() {
    }

    public UserPostRequest(
            Integer id,
            String name,
            String surnames,
            String userName,
            String email,
            String password,
            boolean isEnabled,
            Set<Role> roles
    ) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserPostRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }
}
