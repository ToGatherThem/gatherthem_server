package fr.gatherthem.gatherthem_server.collections.domain.model;

import java.util.UUID;

public class UserModel {
    private UUID id;
    private String username;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
