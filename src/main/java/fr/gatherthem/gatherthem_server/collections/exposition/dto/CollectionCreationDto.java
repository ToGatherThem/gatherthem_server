package fr.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.UUID;

public class CollectionCreationDto {
    private String name;
    private String description;
    private UUID templateId;

    public CollectionCreationDto() {
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

    public boolean isValid() {
        return name != null && !name.isBlank() && templateId != null;
    }
}
