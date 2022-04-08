package fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.service;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.infrastructure.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;


    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionModel> getCollections(){
        return collectionRepository.getCollections();
    }

//    public CollectionModelMongo save(CollectionModelMongo newColl){
//        return collectionRepository.save(newColl);
//    }
//
//    public CollectionModelMongo patch(String id, CollectionModelMongo coll) throws NotFoundException {
//        return collectionRepository.patch(id, coll);
//    }
//
//    public void deleteById(String id) throws NotFoundException {
//        collectionRepository.delete(id);
//    }
}
