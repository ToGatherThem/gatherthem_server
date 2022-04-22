package fr.gatherthem.gatherthem_server.collections.domain.model;

import java.util.UUID;

public class ItemPropertyModel {
    private UUID id;
    private String value;
    private PropertyModel property;

    public ItemPropertyModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PropertyModel getProperty() {
        return property;
    }

    public void setProperty(PropertyModel property) {
        this.property = property;
    }
}
