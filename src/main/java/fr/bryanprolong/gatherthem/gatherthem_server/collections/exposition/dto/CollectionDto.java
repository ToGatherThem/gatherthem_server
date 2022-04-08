package fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.Date;
import java.util.UUID;

public class CollectionDto {
    private UUID id;
    private String name;
    private String description;
    private Date creationDate;

    public CollectionDto() {
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
}
