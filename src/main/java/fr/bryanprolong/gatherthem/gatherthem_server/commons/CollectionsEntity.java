package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document
@Data
@Builder
public class CollectionsEntity {

    @Id
    private UUID uuid;
    private String type;
    private String name;
    private String description;
    private int creation_date;
}
