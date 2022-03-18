package fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.Mapper;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionsDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollectionsRepository {
    private CollectionsDao collectionsDao;

    public CollectionsRepository(CollectionsDao collectionsDao) {
        this.collectionsDao = collectionsDao;
    }

    public List<CollectionModel> getCollections(){
        List<CollectionEntity> collectionEntities = collectionsDao.findAll();
        return collectionEntities.stream().map(Mapper::mapFromEntityToModel).collect(Collectors.toList());
    }
}
