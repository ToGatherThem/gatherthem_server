package fr.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.UUID;

public class ItemPropertyDto {
    private UUID id;
    private String value;
    private PropertyDto property;

    public ItemPropertyDto() {
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

    public PropertyDto getProperty() {
        return property;
    }

    public void setProperty(PropertyDto property) {
        this.property = property;
    }
}
