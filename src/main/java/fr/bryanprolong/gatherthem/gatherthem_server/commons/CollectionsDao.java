package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CollectionsDao extends MongoRepository<CollectionEntity, String> {

    List<CollectionEntity> findByOwnerId(String ownerId);
}
