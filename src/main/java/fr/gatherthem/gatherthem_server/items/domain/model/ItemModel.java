package fr.gatherthem.gatherthem_server.items.domain.model;

import java.util.Date;
import java.util.UUID;

public class ItemModel {
    private UUID id;
    private String label;
    private Date creationDate;
    private Date obtentionDate;
    private CollectionModel collection;

    public ItemModel() {
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

    public CollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CollectionModel collection) {
        this.collection = collection;
    }
}
