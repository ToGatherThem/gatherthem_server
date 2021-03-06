package fr.gatherthem.gatherthem_server.commons.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "label")
    private String label;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB", length=10000000)
    private byte[] image;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "obtention_date")
    private Date obtentionDate;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private CollectionEntity collection;

    @OneToMany(targetEntity = ItemPropertyEntity.class, mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemPropertyEntity> properties;

    public ItemEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationData) {
        this.creationDate = Objects.requireNonNullElseGet(creationData, Date::new);
    }

    public Date getObtentionDate() {
        return obtentionDate;
    }

    public void setObtentionDate(Date obtentionDate) {
        this.obtentionDate = obtentionDate;
    }

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }

    public List<ItemPropertyEntity> getProperties() {
        if(properties == null) return List.of();
        else return properties;
    }

    public void setProperties(List<ItemPropertyEntity> properties) {
        this.properties = properties;
    }
}
