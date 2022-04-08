package fr.gatherthem.gatherthem_server.collections.domain.model;

import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;

import java.util.Date;
import java.util.UUID;

public class CollectionModel {
    private UUID id;
    private String name;
    private String description;
    private Date creationDate;
    private UserModel owner;

    public CollectionModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }


}
