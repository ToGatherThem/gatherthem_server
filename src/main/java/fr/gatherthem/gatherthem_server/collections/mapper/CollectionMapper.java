package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionCreationModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.CollectionUpdateDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.CollectionCreationDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.CollectionDto;
import fr.gatherthem.gatherthem_server.commons.entity.CollectionEntity;

public class CollectionMapper {
    public static CollectionModel mapEntityToModel(CollectionEntity collectionEntity) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setId(collectionEntity.getId());
        collectionModel.setName(collectionEntity.getName());
        collectionModel.setDescription(collectionEntity.getDescription());
        collectionModel.setCreationDate(collectionEntity.getCreationDate());
        collectionModel.setOwner(UserMapper.mapEntityToModel(collectionEntity.getOwner()));
        collectionModel.setTemplate(TemplateMapper.mapEntityToModel(collectionEntity.getTemplate()));
        return collectionModel;
    }

    public static CollectionEntity mapModelToEntity(CollectionModel collectionModel) {
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setId(collectionModel.getId());
        collectionEntity.setName(collectionModel.getName());
        collectionEntity.setDescription(collectionModel.getDescription());
        collectionEntity.setCreationDate(collectionModel.getCreationDate());
        collectionEntity.setOwner(UserMapper.mapModelToEntity(collectionModel.getOwner()));
        collectionEntity.setTemplate(TemplateMapper.mapModelToEntity(collectionModel.getTemplate()));
        return collectionEntity;
    }

    public static CollectionDto mapModelToDto(CollectionModel collectionModel) {
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setId(collectionModel.getId());
        collectionDto.setName(collectionModel.getName());
        collectionDto.setDescription(collectionModel.getDescription());
        collectionDto.setCreationDate(collectionModel.getCreationDate());
        collectionDto.setTemplate(TemplateMapper.mapModelToDto(collectionModel.getTemplate()));
        return collectionDto;
    }

    public static CollectionModel mapInfosDtoToModel(CollectionUpdateDto collectionDto) {
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setName(collectionDto.getName());
        collectionModel.setDescription(collectionDto.getDescription());
        return collectionModel;
    }

    public static CollectionCreationModel mapCreationDtoToCreationModel(CollectionCreationDto collectionCreationDto) {
        CollectionCreationModel collectionCreationModel = new CollectionCreationModel();
        collectionCreationModel.setName(collectionCreationDto.getName());
        collectionCreationModel.setDescription(collectionCreationDto.getDescription());
        collectionCreationModel.setTemplateId(collectionCreationDto.getTemplateId());
        return collectionCreationModel;
    }
}
