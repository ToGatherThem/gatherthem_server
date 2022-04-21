package fr.gatherthem.gatherthem_server.collections.domain.model;

import java.util.UUID;

public class CollectionCreationModel {
    private String name;
    private String description;
    private UUID templateId;

    public CollectionCreationModel() {
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

    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }
}
