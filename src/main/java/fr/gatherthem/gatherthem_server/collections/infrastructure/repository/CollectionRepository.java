package fr.gatherthem.gatherthem_server.collections.infrastructure.repository;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemPropertyCreationModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.TemplateMapper;
import fr.gatherthem.gatherthem_server.commons.dao.CollectionDao;
import fr.gatherthem.gatherthem_server.commons.dao.ItemDao;
import fr.gatherthem.gatherthem_server.commons.dao.ItemPropertyDao;
import fr.gatherthem.gatherthem_server.commons.dao.TemplateDao;
import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import fr.gatherthem.gatherthem_server.commons.entity.ItemPropertyEntity;
import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CollectionRepository {
    private final CollectionDao collectionDao;
    private final ItemDao itemDao;
    private final TemplateDao templateDao;
    private final ItemPropertyDao itemPropertyDao;

    public CollectionRepository(CollectionDao collectionDao, ItemDao itemDao, TemplateDao templateDao, ItemPropertyDao itemPropertyDao) {
        this.collectionDao = collectionDao;
        this.itemDao = itemDao;
        this.templateDao = templateDao;
        this.itemPropertyDao = itemPropertyDao;
    }

    public List<CollectionModel> getCollectionByOwnerId(UUID id) {
        return collectionDao.findAllByUserId(id).stream().map(CollectionMapper::mapEntityToModel).collect(Collectors.toList());
    }

    public List<CollectionModel> getPublicCollections() {
        return collectionDao.findAll().stream().map(CollectionMapper::mapEntityToModel).collect(Collectors.toList());
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

    public ItemModel saveItem(ItemModel item, List<ItemPropertyCreationModel> itemPropertyCreationModel) {
        ItemEntity itemEntity = itemDao.save(ItemMapper.mapModelToEntity(item));

        for(PropertyEntity property : itemEntity.getCollection().getTemplate().getAllProperties()) {
            Optional<ItemPropertyCreationModel> propertyCreationModel = itemPropertyCreationModel.stream().filter(p -> p.getPropertyId().equals(property.getId())).findFirst();
            if(propertyCreationModel.isPresent()) {
                ItemPropertyCreationModel propertyCreation = propertyCreationModel.get();
                ItemPropertyEntity itemProperty = new ItemPropertyEntity();
                itemProperty.setProperty(property);
                itemProperty.setItem(itemEntity);
                itemProperty.setValue(propertyCreation.getValue());
                itemPropertyDao.save(itemProperty);
            }
        }

        return ItemMapper.mapEntityToModel(itemEntity);
    }

    public Optional<TemplateModel> getTemplateById(UUID id) {
        return templateDao.findById(id).map(TemplateMapper::mapEntityToModel);
    }
}
