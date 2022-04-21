package fr.gatherthem.gatherthem_server.items.exposition.dto;

import java.util.Date;

public class ItemUpdateDto {
    private String label;
    private Date obtentionDate;

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
}
