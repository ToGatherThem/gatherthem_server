package fr.gatherthem.gatherthem_server.items.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.ItemDao;
import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemRepository {

    private final ItemDao itemDao;

    public ItemRepository(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Optional<ItemModel> findById(UUID id) {
        return itemDao.findById(id).map(ItemMapper::mapEntityToModel);
    }

    public ItemModel saveItem(ItemModel item) {
        ItemEntity itemEntity = itemDao.save(ItemMapper.mapModelToEntity(item));
        return ItemMapper.mapEntityToModel(itemEntity);
    }

    public void deleteItem(UUID id){ itemDao.deleteById(id);}

    public List<ItemModel> getPublicItems() {
        return itemDao.findAll().stream().map(ItemMapper::mapEntityToModel).toList();
    }

}
