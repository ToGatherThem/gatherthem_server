package fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure.repository;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.dao.CollectionDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollectionRepository {
    private final CollectionDao collectionDao;

    public CollectionRepository(CollectionDao collectionDao) {
        this.collectionDao = collectionDao;
    }

    public List<CollectionModel> getCollections() {
        return collectionDao.findAll().stream().map(CollectionMapper::mapEntityToModel).collect(Collectors.toList());
    }
}
