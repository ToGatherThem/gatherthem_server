package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class MapperEnt {

    public static CollectionEntity mapFromModelToEntity(CollectionModel collectionModel){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new CollectionEntity(collectionModel.getUuid(), collectionModel.getType(), collectionModel.getName(), collectionModel.getDescription(), collectionModel.getCreation_date(), user.getId().toString());
    }
}
