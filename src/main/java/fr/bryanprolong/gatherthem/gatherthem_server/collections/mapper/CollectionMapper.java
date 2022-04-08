package fr.bryanprolong.gatherthem.gatherthem_server.collections.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dto.CollectionCreationAndUpdateDto;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dto.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.CollectionEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.mapper.UserMapper;

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

    public static CollectionDto mapModelToDto(CollectionModel collectionModel) {
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setId(collectionModel.getId());
        collectionDto.setName(collectionModel.getName());
        collectionDto.setDescription(collectionModel.getDescription());
        collectionDto.setCreationDate(collectionModel.getCreationDate());
        return collectionDto;
    }

    public  static CollectionModel mapInfosDtoToModel(CollectionCreationAndUpdateDto collectionDto) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setName(collectionDto.getName());
        collectionModel.setDescription(collectionDto.getDescription());
        return collectionModel;
    }
}
