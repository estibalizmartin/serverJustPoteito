package com.example.serverJustPoteito.auth.model;

import com.example.serverJustPoteito.auth.persistence.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public class UserPostRequest {

    @NotNull
    @NotBlank
    @Length(max = 70)
    private Integer id;
    @NotNull
    @NotBlank
    @Length(max = 70)
    private String name;
    @NotNull
    @NotBlank
    @Length(max = 120)
    private String surnames;
    @NotNull
    @NotBlank
    @Length(max = 70)
    private String userName;
    @NotNull
    @NotBlank
    @Email
    @Length(max = 70)
    private String email;
    @NotNull
    @NotBlank
    @Length(min = 5, max = 70)
    private String password;
    private boolean isEnabled;
    private List<Role> roles;
    private String image;

    public UserPostRequest() {
    }

    public UserPostRequest(String image) {
        this.image = image;
    }

    public UserPostRequest(
            Integer id,
            String name,
            String surnames,
            String userName,
            String email,
            String password,
            boolean isEnabled,
            List<Role> roles
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

    public UserPostRequest(Integer id, String name, String surnames, String userName, String email, String password, boolean isEnabled, List<Role> roles, String image) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
        this.roles = roles;
        this.image = image;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", image='" + image + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
