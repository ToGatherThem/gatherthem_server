package fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.service;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure.repository.CollectionRepository;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.bryanprolong.gatherthem.gatherthem_server.user.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;


    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionModel> getCollections(){
        return collectionRepository.getCollections();
    }


//    public CollectionModelMongo patch(String id, CollectionModelMongo coll) throws NotFoundException {
//        return collectionRepository.patch(id, coll);
//    }
//
    public void deleteById(UUID id) throws NotFoundException {
        collectionRepository.deleteCollection(id);
    }

    public CollectionModel save(CollectionModel coll) {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        coll.setOwner(UserMapper.mapAppUserToUserModel(user));
        return collectionRepository.save(coll);
    }
}
