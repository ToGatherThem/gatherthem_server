package fr.gatherthem.gatherthem_server.collections.domain.model;

import java.util.List;
import java.util.UUID;

public class TemplateModel {
    private UUID id;

    private String name;

    private String description;

    private String itemLabelName;

    private String visibility;

    private TemplateModel parent;

    private UserModel owner;

    private List<PropertyModel> properties;

    public TemplateModel() {
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

    public TemplateModel getParent() {
        return parent;
    }

    public void setParent(TemplateModel parent) {
        this.parent = parent;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public List<PropertyModel> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyModel> properties) {
        this.properties = properties;
    }
}
