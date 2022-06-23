package fr.gatherthem.gatherthem_server.template.exposition.dto;

public class PropertyCreationDto {
    private String name;

    private String type;

    public PropertyCreationDto() {
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
