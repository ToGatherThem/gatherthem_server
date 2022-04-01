package fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition;

import fr.bryanprolong.gatherthem.gatherthem_server.collections.Mapper;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.models.CollectionModel;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.domain.services.CollectionsService;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dtos.CollectionDto;
import fr.bryanprolong.gatherthem.gatherthem_server.collections.exposition.dtos.CollectionInformationsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    private CollectionsService collectionsService;

    public CollectionsController(CollectionsService collectionsService) {
        this.collectionsService = collectionsService;
    }

    @GetMapping()
    public ResponseEntity<List<CollectionDto>> getCollection(){
        try{
            List<CollectionDto> collectionDtos = collectionsService.getCollections().stream().map(Mapper::mapFromModelToDto).collect(Collectors.toList());
            return ResponseEntity.ok(collectionDtos);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<CollectionDto> addCollection(@RequestBody CollectionInformationsDto newColl){
        try{
            CollectionModel coll = Mapper.mapFromInfosDtoToModel(newColl);
            CollectionModel res = collectionsService.save(coll);
            return ResponseEntity.created(URI.create("/collections/" + res.getUuid())).body(Mapper.mapFromModelToDto(res));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping
    public ResponseEntity<CollectionDto> changeCollection(@RequestParam("id") String id, @RequestBody CollectionInformationsDto newColl){
        try{
            CollectionModel coll = Mapper.mapFromInfosDtoToModel(newColl);
            CollectionModel res = collectionsService.patch(id, coll);
            return ResponseEntity.ok(Mapper.mapFromModelToDto(res));
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e){
            return  ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCollection(@RequestParam("id") String id){
        try{
            collectionsService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch(Exception e){
            return  ResponseEntity.internalServerError().build();
        }
    }
}
