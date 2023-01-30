package com.example.serverJustPoteito.auth.model;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class PasswordPostRequest {

    @NotNull
    @Length(min = 5, max = 70)
    private String email;
    @NotNull
    @Length(min = 5, max = 70)
    private String oldPassword;
    @NotNull
    @Length(min = 5, max = 70)
    private String newPassword;

    public PasswordPostRequest() {
    }

    public PasswordPostRequest(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
