package fr.gatherthem.gatherthem_server.template.exposition.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TemplateCreationDto {
    private String name;

    private String description;

    private String itemLabelName;

    private String visibility;

    private UUID parentId;

    private List<PropertyCreationDto> properties;

    public TemplateCreationDto() {
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

    public List<PropertyCreationDto> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyCreationDto> properties) {
        this.properties = Objects.requireNonNullElseGet(properties, ArrayList::new);
    }

    public boolean isValid() {
        return (visibility.equals("PUBLIC") || visibility.equals("PRIVATE")) && name != null && !name.isEmpty() && itemLabelName != null && !itemLabelName.isEmpty() && visibility != null && !visibility.isEmpty();
    }
}
