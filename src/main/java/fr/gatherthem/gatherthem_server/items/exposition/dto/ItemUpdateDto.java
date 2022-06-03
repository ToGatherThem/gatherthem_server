package fr.gatherthem.gatherthem_server.items.exposition.dto;

import java.util.Date;
import java.util.List;

public class ItemUpdateDto {
    private String label;
    private Date obtentionDate;
    private List<ItemPropertyUpdateDto> properties;

    public ItemUpdateDto() {
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getObtentionDate() {
        return this.obtentionDate;
    }

    public void setObtentionDate(Date obtentionDate) {
        this.obtentionDate = obtentionDate;
    }

    public List<ItemPropertyUpdateDto> getProperties() {
        return this.properties;
    }

    public void setProperties(List<ItemPropertyUpdateDto> properties) {
        this.properties = properties;
    }
}
