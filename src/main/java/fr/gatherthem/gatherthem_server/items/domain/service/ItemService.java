package fr.gatherthem.gatherthem_server.items.domain.service;

import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.infrastructure.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemModel updateItem(UUID id, ItemModel item) throws NotFoundException {
        Optional<ItemModel> optionalItemModel = itemRepository.findById(id);
        if (optionalItemModel.isPresent()) {
            ItemModel itemModel = optionalItemModel.get();
            itemModel.setLabel(item.getLabel());
            itemModel.setObtentionDate(item.getObtentionDate());
            return itemRepository.saveItem(itemModel);
        }
        else throw new NotFoundException();
    }

    public void deleteItemById(UUID id) throws NotFoundException{
        itemRepository.deleteItem(id);
    }

}

