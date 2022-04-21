package fr.gatherthem.gatherthem_server.collections.infrastructure.repository;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.TemplateMapper;
import fr.gatherthem.gatherthem_server.commons.dao.CollectionDao;
import fr.gatherthem.gatherthem_server.commons.dao.ItemDao;
import fr.gatherthem.gatherthem_server.commons.dao.TemplateDao;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CollectionRepository {
    private final CollectionDao collectionDao;
    private final ItemDao itemDao;
    private final TemplateDao templateDao;

    public CollectionRepository(CollectionDao collectionDao, ItemDao itemDao, TemplateDao templateDao) {
        this.collectionDao = collectionDao;
        this.itemDao = itemDao;
        this.templateDao = templateDao;
    }

    public List<CollectionModel> getCollectionByOwnerId(UUID id) {
        return collectionDao.findAllByUserId(id).stream().map(CollectionMapper::mapEntityToModel).collect(Collectors.toList());
    }

    public void deleteCollection(UUID id) {
        collectionDao.deleteById(id);
    }

    public CollectionModel saveCollection(CollectionModel coll) {
        return CollectionMapper.mapEntityToModel(collectionDao.save(CollectionMapper.mapModelToEntity(coll)));
    }

    public Optional<CollectionModel> findCollectionById(UUID id) {
        return collectionDao.findById(id).map(CollectionMapper::mapEntityToModel);
    }

    public List<ItemModel> getItemsByCollectionId(UUID id) {
        return itemDao.findByCollectionId(id).stream().map(ItemMapper::mapEntityToModel).collect(Collectors.toList());
    }

    public ItemModel saveItem(ItemModel item) {
        return ItemMapper.mapEntityToModel(itemDao.save(ItemMapper.mapModelToEntity(item)));
    }

    public Optional<TemplateModel> getTemplateById(UUID id) {
        return templateDao.findById(id).map(TemplateMapper::mapEntityToModel);
    }
}
