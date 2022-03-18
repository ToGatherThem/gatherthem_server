package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface CollectionsDao extends MongoRepository<CollectionEntity, String> {
}
