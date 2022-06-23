package fr.gatherthem.gatherthem_server.collections.exposition.controller;

import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionCreationModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.CollectionModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.collections.domain.service.CollectionService;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.*;
import fr.gatherthem.gatherthem_server.collections.mapper.CollectionMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.collections.mapper.ItemPropertyMapper;
import fr.gatherthem.gatherthem_server.commons.exception.ForbiddenException;
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

    /**
     * Gets a user collections
     * @return
     *   <p>200 if the collections were retrieved, with the list of the collections</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping()
    public ResponseEntity<List<CollectionDto>> getCollections(){
        try{
            List<CollectionDto> collections = collectionService.getCollections().stream().map(CollectionMapper::mapModelToDto).toList();
            return ResponseEntity.ok(collections);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Gets all public collections
     * @return
     *   <p>200 if the collections were retrieved, with the list of the collections</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping("/public")
    public ResponseEntity<List<CollectionDto>> getPublicCollections() {
        try {
            List<CollectionDto> collections = collectionService.getPublicCollections().stream().map(CollectionMapper::mapModelToDto).toList();
            return ResponseEntity.ok(collections);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Creates a new collection
     * @param collectionCreationDto the collection to create
     * @return
     *   <p>201 if the collection was successfully created, with the created collection</p>
     *   <p>400 if the collection to create is not valid</p>
     *   <p>403 if the user has reached the maximum number of collections they can have</p>
     *   <p>404 if the template used by the collection doesn't exist</p>
     */
    @PostMapping
    public ResponseEntity<CollectionDto> addCollection(@RequestBody CollectionCreationDto collectionCreationDto){
        if(collectionCreationDto.isValid()) {
            CollectionCreationModel collectionCreationModel = CollectionMapper.mapCreationDtoToCreationModel(collectionCreationDto);

            try {
                CollectionModel collectionModel = collectionService.createCollection(collectionCreationModel);
                return ResponseEntity.created(URI.create("/collections/" + collectionModel.getId())).body(CollectionMapper.mapModelToDto(collectionModel));
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (ForbiddenException e){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else return ResponseEntity.badRequest().build();
    }

    /**
     * Updates a collection
     * @param id the id of the collection to update
     * @param newColl the new collection
     * @return
     *   <p>200 if the collection was successfully updated, with the updated collection</p>
     *   <p>404 if the collection doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
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

    /**
     * Deletes a collection
     * @param id the id of the collection to delete
     * @return
     *   <p>200 if the collection was successfully deleted</p>
     *   <p>404 if the collection doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
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

    /**
     * Creates an item in a collection
     * @param collectionId the id of the collection
     * @param newItem the item to create
     * @return
     *   <p>201 if the item was successfully created, with the created item</p>
     *   <p>403 if the user has reached the maximum number of items they can have in a collection</p>
     *   <p>404 if the collection doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
    @PostMapping("/{id}/items")
    public ResponseEntity<ItemDto> addItem(@PathVariable("id") UUID collectionId, @RequestBody ItemCreationDto newItem){
        try {
            if(newItem.getLabel() == null || newItem.getLabel().isEmpty() || newItem.getLabel().length() > 50 || newItem.getObtentionDate() == null) return ResponseEntity.badRequest().build();
            ItemModel item = ItemMapper.mapCreationDtoToCreationModel(newItem);
            ItemModel res = collectionService.saveItem(collectionId, item, newItem.getProperties().stream().map(ItemPropertyMapper::mapCreationDtoToCreationModel).toList());
            return ResponseEntity.created(URI.create("/collections/" + collectionId + "/items/" + res.getId())).body(ItemMapper.mapModelToDto(res));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Gets items of a collection
     * @param id the id of the collection
     * @return
     *   <p>200 if the items were retrieved, with the list of the items</p>
     *   <p>400 if the collection is not valid</p>
     *   <p>403 if the collection doesn't belong to the authenticated user</p>
     *   <p>404 if the collection doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDto>> getItems(@PathVariable("id") String id){
        try{
            List<ItemDto> items = collectionService.getItemsByCollectionId(UUID.fromString(id)).stream().map(ItemMapper::mapModelToDto).toList();
            return ResponseEntity.ok(items);
        }catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
}
