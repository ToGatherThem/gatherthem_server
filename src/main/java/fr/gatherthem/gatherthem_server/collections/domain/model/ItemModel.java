package fr.gatherthem.gatherthem_server.collections.domain.model;

import java.util.*;

public class ItemModel {
    private UUID id;
    private String label;
    private byte[] image;
    private Date creationDate;
    private Date obtentionDate;
    private CollectionModel collection;
    private List<ItemPropertyModel> properties;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public List<ItemPropertyModel> getProperties() {
        if(properties == null) return List.of();
        else return properties;
    }

    public void setProperties(List<ItemPropertyModel> properties) {
        this.properties = properties;
    }
}
