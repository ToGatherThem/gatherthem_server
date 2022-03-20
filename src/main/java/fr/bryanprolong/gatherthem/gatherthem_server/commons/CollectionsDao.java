package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface CollectionsDao extends MongoRepository<CollectionEntity, UUID> {
}
