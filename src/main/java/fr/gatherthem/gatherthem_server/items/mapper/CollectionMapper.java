package fr.gatherthem.gatherthem_server.items.mapper;


import fr.gatherthem.gatherthem_server.commons.entity.CollectionEntity;
import fr.gatherthem.gatherthem_server.items.domain.model.CollectionModel;

public class CollectionMapper {

    public static CollectionModel mapEntityToModel(CollectionEntity collectionEntity) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setId(collectionEntity.getId());
        collectionModel.setName(collectionEntity.getName());
        collectionModel.setDescription(collectionEntity.getDescription());
        collectionModel.setCreationDate(collectionEntity.getCreationDate());
        collectionModel.setOwner(UserMapper.mapEntityToModel(collectionEntity.getOwner()));
        return collectionModel;
    }

    public static CollectionEntity mapModelToEntity(CollectionModel collectionModel) {
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setId(collectionModel.getId());
        collectionEntity.setName(collectionModel.getName());
        collectionEntity.setDescription(collectionModel.getDescription());
        collectionEntity.setCreationDate(collectionModel.getCreationDate());
        collectionEntity.setOwner(UserMapper.mapModelToEntity(collectionModel.getOwner()));
        return collectionEntity;
    }
}
