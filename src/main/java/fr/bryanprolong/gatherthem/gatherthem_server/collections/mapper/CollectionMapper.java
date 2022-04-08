package fr.bryanprolong.gatherthem.gatherthem_server.collections.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dto.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.entitie.CollectionEntity;

public class CollectionMapper {
    public static CollectionModel mapEntityToModel(CollectionEntity collectionEntity) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setId(collectionEntity.getId());
        collectionModel.setName(collectionEntity.getName());
        collectionModel.setDescription(collectionEntity.getDescription());
        collectionModel.setCreationDate(collectionEntity.getCreationDate());
        collectionModel.setOwner(collectionEntity.getOwner());
        return collectionModel;
    }

    public static CollectionEntity mapModelToEntity(CollectionModel collectionModel) {
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setId(collectionModel.getId());
        collectionEntity.setName(collectionModel.getName());
        collectionEntity.setDescription(collectionModel.getDescription());
        collectionEntity.setCreationDate(collectionModel.getCreationDate());
        collectionEntity.setOwner(collectionModel.getOwner());
        return collectionEntity;
    }

    public static CollectionDto mapModelToDto(CollectionModel collectionModel) {
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setId(collectionModel.getId());
        collectionDto.setName(collectionModel.getName());
        collectionDto.setDescription(collectionModel.getDescription());
        collectionDto.setCreationDate(collectionModel.getCreationDate());
        return collectionDto;
    }
}
