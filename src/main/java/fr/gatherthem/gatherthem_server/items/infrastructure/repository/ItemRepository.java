package fr.gatherthem.gatherthem_server.items.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.ItemDao;
import fr.gatherthem.gatherthem_server.commons.dao.ItemPropertyDao;
import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import fr.gatherthem.gatherthem_server.commons.entity.ItemPropertyEntity;
import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemPropertyModel;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemPropertyUpdateModel;
import fr.gatherthem.gatherthem_server.items.mapper.ItemMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ItemRepository {

    private final ItemDao itemDao;
    private final ItemPropertyDao itemPropertyDao;

    public ItemRepository(ItemDao itemDao, ItemPropertyDao itemPropertyDao) {
        this.itemDao = itemDao;
        this.itemPropertyDao = itemPropertyDao;
    }

    public Optional<ItemModel> findById(UUID id) {
        return itemDao.findById(id).map(ItemMapper::mapEntityToModel);
    }

    public ItemModel saveItem(ItemModel item, List<ItemPropertyUpdateModel> itemPropertyModels) {
        ItemEntity itemEntity = itemDao.save(ItemMapper.mapModelToEntity(item));

        for (PropertyEntity property : itemEntity.getCollection().getTemplate().getAllProperties()) {
            Optional<ItemPropertyUpdateModel> itemPropertyModel = itemPropertyModels.stream().filter(p -> p.getPropertyId().equals(property.getId())).findFirst();
            if (itemPropertyModel.isPresent()) {
                ItemPropertyUpdateModel propertyUpdate = itemPropertyModel.get();
                ItemPropertyEntity itemProperty = new ItemPropertyEntity();
                itemProperty.setProperty(property);
                itemProperty.setItem(itemEntity);
                itemProperty.setValue(propertyUpdate.getValue());
                itemPropertyDao.save(itemProperty);
            }
        }

        return ItemMapper.mapEntityToModel(itemEntity);
    }

    public void deleteItem(UUID id){ itemDao.deleteById(id);}

    public List<ItemModel> getPublicItems() {
        return itemDao.findAll().stream().map(ItemMapper::mapEntityToModel).toList();
    }

}
