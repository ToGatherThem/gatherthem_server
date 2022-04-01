package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.UUID;


@Document(collection = "collections")
@Data
@Builder
public class CollectionEntity {

    @Id
    private String _id;
    private String type;
    private String name;
    private String description;
    private long created_at;
    private String ownerId;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void updateData(CollectionEntity collInfos){
        if(collInfos.name != null){
            this.name = collInfos.name;
        }
        if(collInfos.type != null){
            this.type = collInfos.type;
        }
        if(collInfos.description != null){
            this.description = collInfos.description;
        }
        if(collInfos.created_at != 0){
            this.created_at = collInfos.created_at;
        }

    }
}
