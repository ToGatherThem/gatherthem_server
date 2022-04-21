package fr.gatherthem.gatherthem_server.collections.exposition.controller;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionCreationModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.domain.service.CollectionService;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.*;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.commons.exception.Forbidden;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CollectionDto> addCollection(@RequestBody CollectionCreationDto collectionCreationDto){
        if(collectionCreationDto.isValid()) {
            CollectionCreationModel collectionCreationModel = CollectionMapper.mapCreationDtoToCreationModel(collectionCreationDto);

            try {
                CollectionModel collectionModel = collectionService.createCollection(collectionCreationModel);
                return ResponseEntity.created(URI.create("/collections/" + collectionModel.getId())).body(CollectionMapper.mapModelToDto(collectionModel));
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        } else return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<CollectionDto> changeCollection(@RequestParam("id") UUID id, @RequestBody CollectionUpdateDto newColl){
        try{
            CollectionModel coll = CollectionMapper.mapInfosDtoToModel(newColl);
            CollectionModel res = collectionService.updateCollection(id, coll);
            return ResponseEntity.ok(CollectionMapper.mapModelToDto(res));
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e){
            return  ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCollection(@RequestParam("id") UUID id){
        try{
            collectionService.deleteCollectionById(id);
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
            if(newItem.getLabel() == null || newItem.getLabel().isEmpty() || newItem.getLabel().length() > 50 || newItem.getObtentionDate() == null) return ResponseEntity.badRequest().build();
            ItemModel item = ItemMapper.mapCreationAndUpdateDtoToModel(newItem);
            ItemModel res = collectionService.saveItem(collectionId, item);
            return ResponseEntity.created(URI.create("/collections/" + collectionId + "/items/" + res.getId())).body(ItemMapper.mapModelToDto(res));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("id") String id){
        try{
            List<ItemDto> items = collectionService.getItemsByCollectionId(UUID.fromString(id)).stream().map(ItemMapper::mapModelToDto).toList();
            return ResponseEntity.ok(items);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Forbidden e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
}
