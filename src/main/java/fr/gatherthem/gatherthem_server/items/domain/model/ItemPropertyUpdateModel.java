package fr.gatherthem.gatherthem_server.items.domain.model;

import java.util.UUID;

public class ItemPropertyUpdateModel {
    private UUID id;
    private UUID propertyId;
    private String value;

    public ItemPropertyUpdateModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
