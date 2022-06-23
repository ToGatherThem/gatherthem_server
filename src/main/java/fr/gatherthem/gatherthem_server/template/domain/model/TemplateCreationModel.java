package fr.gatherthem.gatherthem_server.template.domain.model;

import java.util.List;
import java.util.UUID;

public class TemplateCreationModel {
    private String name;

    private String description;

    private String itemLabelName;

    private String visibility;

    private UUID parentId;

    private UUID ownerId;

    private List<PropertyModel> properties;

    public TemplateCreationModel() {
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

    public String getItemLabelName() {
        return itemLabelName;
    }

    public void setItemLabelName(String itemLabelName) {
        this.itemLabelName = itemLabelName;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public List<PropertyModel> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyModel> properties) {
        this.properties = properties;
    }
}
