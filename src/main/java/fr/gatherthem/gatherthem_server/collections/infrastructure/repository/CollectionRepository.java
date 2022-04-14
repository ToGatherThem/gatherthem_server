package fr.gatherthem.gatherthem_server.collections.infrastructure.repository;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.commons.dao.CollectionDao;
import fr.gatherthem.gatherthem_server.commons.dao.ItemDao;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CollectionRepository {
    private final CollectionDao collectionDao;
    private final ItemDao itemDao;

    public CollectionRepository(CollectionDao collectionDao, ItemDao itemDao) {
        this.collectionDao = collectionDao;
        this.itemDao = itemDao;
    }

    public List<CollectionModel> getCollections() {
        return collectionDao.findAll().stream().map(CollectionMapper::mapEntityToModel).collect(Collectors.toList());
    }

    public void deleteCollection(UUID id) {
        collectionDao.deleteById(id);
    }

    public CollectionModel save(CollectionModel coll) {
        return CollectionMapper.mapEntityToModel(collectionDao.save(CollectionMapper.mapModelToEntity(coll)));
    }

    public Optional<CollectionModel> findById(UUID id) {
        return collectionDao.findById(id).map(CollectionMapper::mapEntityToModel);
    }

    public ItemModel saveItem(ItemModel item) {
        return ItemMapper.mapEntityToModel(itemDao.save(ItemMapper.mapModelToEntity(item)));
    }
}
