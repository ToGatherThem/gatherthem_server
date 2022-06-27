package fr.gatherthem.gatherthem_server.collections.domain.service;

import fr.gatherthem.gatherthem_server.collections.domain.model.*;
import fr.gatherthem.gatherthem_server.collections.infrastructure.repository.CollectionRepository;
import fr.gatherthem.gatherthem_server.commons.exception.ForbiddenException;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.collections.mapper.UserMapper;
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

    public List<CollectionModel> getPublicCollections(){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return collectionRepository.getPublicCollections().stream().filter(collectionModel -> collectionModel.getOwner().getId() == user.getId()).toList();
    }

    public CollectionModel updateCollection(UUID id, CollectionModel coll) throws NotFoundException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(id);
        if(optionalCollectionModel.isPresent()){
            CollectionModel collectionModel = optionalCollectionModel.get();
            collectionModel.setName(coll.getName());
            collectionModel.setDescription(coll.getDescription());
            collectionModel.setImage(coll.getImage());
            return collectionRepository.saveCollection(collectionModel);
        }
        else throw new NotFoundException();
    }

    public void deleteCollectionById(UUID id) throws NotFoundException {
        collectionRepository.deleteCollection(id);
    }

    public CollectionModel createCollection(CollectionCreationModel collectionCreationModel) throws NotFoundException, ForbiddenException {
        Optional<TemplateModel> optionalTemplateModel = collectionRepository.getTemplateById(collectionCreationModel.getTemplateId());
        if(optionalTemplateModel.isPresent()){
            TemplateModel templateModel = optionalTemplateModel.get();
            AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getAuthorityList().stream().noneMatch(authority -> authority.getCode().equals("PREMIUM")) && collectionRepository.getNumberOfCollectionByUserId(user.getId()) >= 2) {
                throw new ForbiddenException();
            }
            CollectionModel collectionToCreate = new CollectionModel();
            collectionToCreate.setName(collectionCreationModel.getName());
            collectionToCreate.setDescription(collectionCreationModel.getDescription());
            collectionToCreate.setImage(collectionCreationModel.getImage());
            collectionToCreate.setOwner(UserMapper.mapAppUserToUserModel(user));
            collectionToCreate.setTemplate(templateModel);

            return collectionRepository.saveCollection(collectionToCreate);
        } else throw new NotFoundException();
    }

    public List<ItemModel> getItemsByCollectionId(UUID id) throws NotFoundException, ForbiddenException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(id);
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(optionalCollectionModel.isPresent()){
            CollectionModel collectionModel = optionalCollectionModel.get();
            if(collectionModel.getOwner().getId().equals(user.getId())){
                return collectionRepository.getItemsByCollectionId(id);
            } else throw new ForbiddenException();
        } else throw new NotFoundException();
    }

    public ItemModel saveItem(UUID collectionId, ItemModel item, List<ItemPropertyCreationModel> propertyCreationModels) throws NotFoundException, ForbiddenException {
        Optional<CollectionModel> optionalCollectionModel = collectionRepository.findCollectionById(collectionId);
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(optionalCollectionModel.isPresent()){
            if (user.getAuthorityList().stream().noneMatch(authority -> authority.getCode().equals("PREMIUM")) && collectionRepository.findNumberOfItemsByCollectionId(optionalCollectionModel.get().getId()) >= 50){
                throw new ForbiddenException();
            }
            item.setCollection(optionalCollectionModel.get());
            if(optionalCollectionModel.get().getOwner().getId().equals(user.getId())){
                return collectionRepository.saveItem(item, propertyCreationModels);
            } else throw new ForbiddenException();
        }
        else throw new NotFoundException();
    }
}
