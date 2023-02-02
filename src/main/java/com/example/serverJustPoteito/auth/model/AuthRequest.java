package com.example.serverJustPoteito.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AuthRequest {

    @NotNull
    @NotBlank
    @Length(max = 70)
    private String name;
    @NotNull
    @NotBlank
    @Length(max = 70)
    private String userName;
    @NotNull
    @NotBlank
    @Length(max = 120)
    private String surnames;
    @NotNull
    @NotBlank
    @Length(max = 70)
    private String email;
    @NotNull
    @NotBlank
    @Length(min = 5, max = 70)
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthRequest(String name, String userName, String surnames, String email, String password) {
        this.name = name;
        this.userName = userName;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
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

    @Override
    public String toString() {
        return "AuthRequest{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", surnames='" + surnames + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
