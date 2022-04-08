package fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserModel {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private List<Authority> authorities;

    public UserModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorities() {
        if(authorities != null) return authorities;
        else return new ArrayList<>();
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
