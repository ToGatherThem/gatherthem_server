package fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.controller;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.service.CollectionService;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dto.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping()
    public ResponseEntity<List<CollectionDto>> getCollection(){
        try{
            List<CollectionDto> collections = collectionService.getCollections().stream().map(CollectionMapper::mapModelToDto).toList();
            return ResponseEntity.ok(collections);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<CollectionDtoMongo> addCollection(@RequestBody CollectionInformationsDto newColl){
//        try{
//            CollectionModelMongo coll = Mapper.mapFromInfosDtoToModel(newColl);
//            CollectionModelMongo res = collectionsService.save(coll);
//            return ResponseEntity.created(URI.create("/collections/" + res.getUuid())).body(Mapper.mapFromModelToDto(res));
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @PatchMapping
//    public ResponseEntity<CollectionDtoMongo> changeCollection(@RequestParam("id") String id, @RequestBody CollectionInformationsDto newColl){
//        try{
//            CollectionModelMongo coll = Mapper.mapFromInfosDtoToModel(newColl);
//            CollectionModelMongo res = collectionsService.patch(id, coll);
//            return ResponseEntity.ok(Mapper.mapFromModelToDto(res));
//        }catch (NotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//        catch(Exception e){
//            return  ResponseEntity.internalServerError().build();
//        }
//    }
//
    @DeleteMapping
    public ResponseEntity<Void> deleteCollection(@RequestParam("id") UUID id){
        try{
            collectionService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch(Exception e){
            return  ResponseEntity.internalServerError().build();
        }
    }
}
