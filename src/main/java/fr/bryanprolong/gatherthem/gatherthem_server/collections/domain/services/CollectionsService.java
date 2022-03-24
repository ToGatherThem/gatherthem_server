package fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.services;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure.CollectionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionsService {
    private CollectionsRepository collectionsRepository;


    public CollectionsService(CollectionsRepository collectionsRepository) {
        this.collectionsRepository = collectionsRepository;
    }

    public List<CollectionModel> getCollections(){
        return collectionsRepository.getCollections();
    }

    public CollectionModel save(CollectionModel newColl){
        return collectionsRepository.save(newColl);
    }

    public CollectionModel patch(String id, CollectionModel coll) throws NotFoundException {
        return collectionsRepository.patch(id, coll);
    }

    public void deleteById(String id) throws NotFoundException {
        collectionsRepository.delete(id);
    }
}