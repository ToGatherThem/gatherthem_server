package fr.gatherthem.gatherthem_server.items.exposition.controller;

import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.items.domain.service.ItemService;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemUpdateDto;
import fr.gatherthem.gatherthem_server.items.mapper.ItemMapper;
import fr.gatherthem.gatherthem_server.items.mapper.ItemPropertyMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Gets an item information
     * @param id the id of the item to retrieve
     * @return
     *   <p>200 if the item was successfully retrieved, with its information</p>
     *   <p>404 if the item doesn't exist</p>
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable UUID id) {
        try {
            ItemModel itemModel = itemService.getItem(id);
            return ResponseEntity.ok(ItemMapper.mapModelToDto(itemModel));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates an item
     * @param id the id of the item to update
     * @param itemDto the new item
     * @return
     *   <p>200 if the item was successfully updated, with the updated item</p>
     *   <p>404 if the item doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> editItem(@PathVariable UUID id, @RequestBody ItemUpdateDto itemDto) {
        try{
            ItemModel itemModel = ItemMapper.mapUpdateDtoToModel(itemDto);
            ItemModel res = itemService.updateItem(id, itemModel, itemDto.getProperties().stream().map(ItemPropertyMapper::mapDtoToUpdateModel).toList());
            return ResponseEntity.ok(ItemMapper.mapModelToDto(res));
        }
        catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Deletes an item
     * @param id the id of the item to delete
     * @return
     *   <p>200 if the item was successfully deleted</p>
     *   <p>404 if the item doesn't exist</p>
     *   <p>500 if an error occurred</p>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        try {
            itemService.deleteItemById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Gets all public items
     * @return
     *   <p>200 if the items were retrieved, with the list of the items</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping("/public")
    public ResponseEntity<List<ItemDto>> getPublicItems() {
        try {
            List<ItemDto> items = itemService.getPublicItems().stream().map(ItemMapper::mapModelToDto).toList();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
