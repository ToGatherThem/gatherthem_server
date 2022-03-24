package fr.bryanprolong.gatherthem.gatherthem_server.collections;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dtos.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dtos.CollectionInformationsDto;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionEntity;

public class Mapper {

    public static CollectionModel mapFromEntityToModel(CollectionEntity collectionEntity){
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setUuid(collectionEntity.getId());
        collectionModel.setName(collectionEntity.getName());
        collectionModel.setDescription(collectionEntity.getDescription());
        collectionModel.setType(collectionEntity.getType());
        collectionModel.setCreation_date(collectionEntity.getCreated_at());
        return collectionModel;
    }

    public static CollectionDto mapFromModelToDto(CollectionModel collectionModel){
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setId(collectionModel.getUuid());
        collectionDto.setType(collectionModel.getType());
        collectionDto.setCreationDate(collectionModel.getCreation_date());
        collectionDto.setDescription(collectionModel.getDescription());
        collectionDto.setName(collectionModel.getName());
        return collectionDto;
    }

    public static CollectionModel mapFromDtoToModel(CollectionDto collectionDto){
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setUuid(collectionDto.getId());
        collectionModel.setName(collectionDto.getName());
        collectionModel.setDescription(collectionDto.getDescription());
        collectionModel.setType(collectionDto.getType());
        collectionModel.setCreation_date(collectionDto.getCreationDate());
        return collectionModel;
    }

    public static CollectionModel mapFromInfosDtoToModel(CollectionInformationsDto collectionInformationsDto){
        CollectionModel collectionModel = new CollectionModel();
        collectionModel.setName(collectionInformationsDto.getName());
        collectionModel.setDescription(collectionInformationsDto.getDescription());
        collectionModel.setType(collectionInformationsDto.getType());
        collectionModel.setCreation_date(collectionInformationsDto.getCreationDate());
        return collectionModel;
    }
}
