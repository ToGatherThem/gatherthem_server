package fr.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.UUID;

public class ItemPropertyCreationDto {
    private UUID propertyId;
    private String value;

    public ItemPropertyCreationDto() {
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
