package fr.gatherthem.gatherthem_server.collections.exposition.dto;

import java.util.Date;

public class ItemDto {
    private String label;
    private Date obtentionDate;

    public ItemDto() {
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
}
