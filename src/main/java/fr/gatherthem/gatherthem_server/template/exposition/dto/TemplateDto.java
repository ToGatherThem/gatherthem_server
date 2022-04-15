package fr.gatherthem.gatherthem_server.template.exposition.dto;

import java.util.UUID;

public class TemplateDto {
    private UUID id;

    private String name;

    private String description;

    private String itemLabelName;

    private String visibility;

    private TemplateDto parent;

    private UserDto owner;

    public TemplateDto() {
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

    public TemplateDto getParent() {
        return parent;
    }

    public void setParent(TemplateDto parent) {
        this.parent = parent;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }
}
