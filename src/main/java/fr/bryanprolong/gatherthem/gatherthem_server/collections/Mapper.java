package fr.bryanprolong.gatherthem.gatherthem_server.collections;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dtos.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionEntity;

public class Mapper {

    public static CollectionModel mapFromEntityToModel(CollectionEntity collectionEntity){
        CollectionModel collectionModel = new CollectionModel();
        //collectionModel.setUuid(collectionEntity.getId());
        collectionModel.setName(collectionEntity.getName());
        collectionModel.setDescription(collectionEntity.getDescription());
        collectionModel.setType(collectionEntity.getType());
        collectionModel.setCreation_date(collectionEntity.getCreated_at());
        return collectionModel;
    }

    public static CollectionDto mapFromModelToDto(CollectionModel collectionModel){
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setType(collectionModel.getType());
        collectionDto.setCreationDate(collectionModel.getCreation_date());
        collectionDto.setDescription(collectionModel.getDescription());
        collectionDto.setName(collectionModel.getName());
        return collectionDto;
    }

    public static CollectionModel mapFromDtoToModel(CollectionDto collectionDto){
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setName(collectionDto.getName());
        collectionModel.setDescription(collectionDto.getDescription());
        collectionModel.setType(collectionDto.getType());
        collectionModel.setCreation_date(collectionDto.getCreationDate());
        return collectionModel;
    }

    /*public static CollectionEntity mapFromModelToEntity(CollectionModel collectionModel){
        CollectionEntity collectionEntity = new CollectionEntity();
        collectionEntity.setType(collectionEntity.getType());
        collectionEntity.setCreationDate(collectionEntity.getCreation_date());
        collectionEntity.setDescription(collectionEntity.getDescription());
        collectionEntity.setName(collectionEntity.getName());
        return collectionEntity;
    }*/
}
