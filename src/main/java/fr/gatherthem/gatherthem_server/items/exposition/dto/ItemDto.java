package fr.gatherthem.gatherthem_server.items.exposition.dto;

import java.util.Date;
import java.util.UUID;

public class ItemDto {
    private UUID id;
    private String label;
    private Date creationDate;
    private Date obtentionDate;

    public ItemDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getObtentionDate() {
        return obtentionDate;
    }

    public void setObtentionDate(Date obtentionDate) {
        this.obtentionDate = obtentionDate;
    }
}
