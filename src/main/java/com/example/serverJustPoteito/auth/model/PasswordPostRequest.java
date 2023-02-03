package com.example.serverJustPoteito.auth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class PasswordPostRequest {

    @NotNull
    @NotBlank
    private Integer id;
    @NotNull
    @Length(min = 5, max = 70)
    private String oldPassword;
    @NotNull
    @Length(min = 5, max = 70)
    private String newPassword;

    public PasswordPostRequest() {
    }

    public PasswordPostRequest(Integer id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(Integer id) {
        this.id = id;
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
