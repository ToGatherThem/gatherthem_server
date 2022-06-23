package fr.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.Date;
import java.util.List;

public class ItemCreationDto {
    private String label;
    private byte[] image;
    private Date obtentionDate;
    private List<ItemPropertyCreationDto> properties;

    public ItemCreationDto() {
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getObtentionDate() {
        return this.obtentionDate;
    }

    public void setObtentionDate(Date obtentionDate) {
        this.obtentionDate = obtentionDate;
    }

    public List<ItemPropertyCreationDto> getProperties() {
        return properties;
    }

    public void setProperties(List<ItemPropertyCreationDto> properties) {
        this.properties = properties;
    }
}
