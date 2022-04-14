package fr.gatherthem.gatherthem_server.collections.exposition.controller;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.domain.service.CollectionService;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.CollectionDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.CollectionCreationAndUpdateDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemCreationAndUpdateDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<CollectionDto> addCollection(@RequestBody CollectionCreationAndUpdateDto newColl){
        try{
            CollectionModel coll = CollectionMapper.mapInfosDtoToModel(newColl);
            CollectionModel res = collectionService.save(coll);
            return ResponseEntity.created(URI.create("/collections/" + res.getId())).body(CollectionMapper.mapModelToDto(res));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<CollectionDto> changeCollection(@RequestParam("id") UUID id, @RequestBody CollectionCreationAndUpdateDto newColl){
        try{
            CollectionModel coll = CollectionMapper.mapInfosDtoToModel(newColl);
            CollectionModel res = collectionService.update(id, coll);
            return ResponseEntity.ok(CollectionMapper.mapModelToDto(res));
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e){
            return  ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCollection(@RequestParam("id") UUID id) {
        try {
            collectionService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ItemDto> addItem(@PathVariable("id") UUID collectionId, @RequestBody ItemCreationAndUpdateDto newItem){
        try {
            ItemModel item = ItemMapper.mapCreationAndUpdateDtoToModel(newItem);
            ItemModel res = collectionService.saveItem(collectionId, item);
            return ResponseEntity.created(URI.create("/collections/" + collectionId + "/items/" + res.getId())).body(ItemMapper.mapModelToDto(res));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
