package fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.Mapper;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.CollectionsDao;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.MapperEnt;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CollectionsRepository {
    private final CollectionsDao collectionsDao;

    public CollectionsRepository(CollectionsDao collectionsDao) {
        this.collectionsDao = collectionsDao;
    }

    public List<CollectionModel> getCollections(){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CollectionEntity> collectionEntities = collectionsDao.findByOwnerId(user.getId().toString());
        return collectionEntities.stream().map(Mapper::mapFromEntityToModel).collect(Collectors.toList());
    }

    public CollectionModel save(CollectionModel newColl){
        CollectionEntity entity = MapperEnt.mapFromModelToEntity(newColl);
        CollectionEntity res = collectionsDao.save(entity);
        return Mapper.mapFromEntityToModel(res);
    }

    public CollectionModel patch(String id, CollectionModel coll) throws NotFoundException {
        Optional<CollectionEntity> oldEntity = collectionsDao.findById(id);
        if (oldEntity.isEmpty()){
            throw new NotFoundException();
        }
        else{
            oldEntity.get().updateData(MapperEnt.mapFromModelToEntity(coll));
            CollectionEntity res = collectionsDao.save(oldEntity.get());
            return Mapper.mapFromEntityToModel(res);
        }
    }

    public void delete(String id) throws NotFoundException {
        Optional<CollectionEntity> collection = collectionsDao.findById(id);
        if (collection.isEmpty())
            throw new NotFoundException();
        collectionsDao.deleteById(id);
    }
}
