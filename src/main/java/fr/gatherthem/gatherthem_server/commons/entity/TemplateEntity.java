package fr.gatherthem.gatherthem_server.commons.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "template")
public class TemplateEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "item_label_name")
    private String itemLabelName;

    @Column(name = "visibility")
    private String visibility;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TemplateEntity parent;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToMany(targetEntity = PropertyEntity.class, mappedBy = "template", cascade = CascadeType.ALL)
    private List<PropertyEntity> properties;

    public TemplateEntity() {
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

    public TemplateEntity getParent() {
        return parent;
    }

    public void setParent(TemplateEntity parent) {
        this.parent = parent;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<PropertyEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyEntity> properties) {
        this.properties = properties;
    }
}
