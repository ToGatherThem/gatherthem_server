package fr.gatherthem.gatherthem_server.collections.domain.service;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.infrastructure.repository.CollectionRepository;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.user.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    public CollectionModel update(UUID id, CollectionModel coll) throws NotFoundException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findById(id);
        if(optionalCollectionModel.isPresent()){
            CollectionModel collectionModel = optionalCollectionModel.get();
            collectionModel.setName(coll.getName());
            collectionModel.setDescription(coll.getDescription());
            return collectionRepository.save(collectionModel);
        }
        else throw new NotFoundException();

    }

    public void deleteById(UUID id) throws NotFoundException {
        collectionRepository.deleteCollection(id);
    }

    public CollectionModel save(CollectionModel coll) {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        coll.setOwner(UserMapper.mapAppUserToUserModel(user));
        return collectionRepository.save(coll);
    }
}
