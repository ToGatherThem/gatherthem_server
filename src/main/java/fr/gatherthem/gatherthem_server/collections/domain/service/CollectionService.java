package fr.gatherthem.gatherthem_server.collections.domain.service;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.infrastructure.repository.CollectionRepository;
import fr.gatherthem.gatherthem_server.commons.exception.Forbidden;
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
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionModel> getCollections(){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return collectionRepository.getCollectionByOwnerId(user.getId());
    }


    public CollectionModel updateCollection(UUID id, CollectionModel coll) throws NotFoundException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(id);
        if(optionalCollectionModel.isPresent()){
            CollectionModel collectionModel = optionalCollectionModel.get();
            collectionModel.setName(coll.getName());
            collectionModel.setDescription(coll.getDescription());
            return collectionRepository.saveCollection(collectionModel);
        }
        else throw new NotFoundException();

    }

    public void deleteCollectionById(UUID id) throws NotFoundException {
        collectionRepository.deleteCollection(id);
    }

    public CollectionModel saveCollection(CollectionModel coll) {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        coll.setOwner(UserMapper.mapAppUserToUserModel(user));
        return collectionRepository.saveCollection(coll);
    }

    public List<ItemModel> getItemsByCollectionId(UUID id) throws NotFoundException, Forbidden {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(id);
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(optionalCollectionModel.isPresent()){
            CollectionModel collectionModel = optionalCollectionModel.get();
            if(collectionModel.getOwner().getId().equals(user.getId())){
                return collectionRepository.getItemsByCollectionId(id);
            } else throw new Forbidden();
        } else throw new NotFoundException();
    }

    public ItemModel saveItem(UUID collectionId, ItemModel item) throws NotFoundException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(collectionId);
        if(optionalCollectionModel.isPresent()){
            item.setCollection(optionalCollectionModel.get());
            return collectionRepository.saveItem(item);
        }
        else throw new NotFoundException();
    }
}
