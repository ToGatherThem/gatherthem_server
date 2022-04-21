package fr.gatherthem.gatherthem_server.items.exposition.controller;

import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.items.domain.service.ItemService;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemUpdateDto;
import fr.gatherthem.gatherthem_server.items.mapper.ItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PutMapping
    public ResponseEntity<ItemDto> editItem(@RequestParam("id") UUID id, @RequestBody ItemUpdateDto itemDto) {
        try{
            ItemModel itemModel = ItemMapper.mapUpdateDtoToModel(itemDto);
            ItemModel res = itemService.updateItem(id, itemModel);
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


    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@RequestParam("id")UUID id) {
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
}
