package fr.gatherthem.gatherthem_server.items.exposition.dto;

import java.util.UUID;

public class ItemPropertyUpdateDto {
    private UUID propertyId;
    private String value;

    public ItemPropertyUpdateDto() {
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
