package fr.gatherthem.gatherthem_server.items.domain.model;

import java.util.UUID;

public class PropertyModel {
    private UUID id;
    private String name;
    private String type;

    public PropertyModel() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
