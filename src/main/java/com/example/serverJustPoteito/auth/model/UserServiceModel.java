package com.example.serverJustPoteito.auth.model;

import com.example.serverJustPoteito.auth.persistence.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserServiceModel implements UserDetails {
    @NotNull
    @NotBlank
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
    private Set<Role> roles;

    public UserServiceModel() {
    }

    public UserServiceModel(
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

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

}
