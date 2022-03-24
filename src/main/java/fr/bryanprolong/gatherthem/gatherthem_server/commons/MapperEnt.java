package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;

public class MapperEnt {

    public static CollectionEntity mapFromModelToEntity(CollectionModel collectionModel){
        CollectionEntity collectionEntity = new CollectionEntity(collectionModel.getUuid(), collectionModel.getType(), collectionModel.getName(), collectionModel.getDescription(), collectionModel.getCreation_date());
        return collectionEntity;
    }
}
