package fr.gatherthem.gatherthem_server.items.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemModel;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemUpdateDto;

public class ItemMapper {

    public static ItemModel mapEntityToModel(ItemEntity itemEntity) {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemEntity.getId());
        itemModel.setLabel(itemEntity.getLabel());
        itemModel.setCreationDate(itemEntity.getCreationDate());
        itemModel.setObtentionDate(itemEntity.getObtentionDate());
        itemModel.setCollection(CollectionMapper.mapEntityToModel(itemEntity.getCollection()));
        itemModel.setProperties(itemEntity.getProperties().stream().map(ItemPropertyMapper::mapEntityToModel).toList());
        return itemModel;
    }

    public static ItemEntity mapModelToEntity(ItemModel itemModel) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemModel.getId());
        itemEntity.setLabel(itemModel.getLabel());
        itemEntity.setCreationDate(itemModel.getCreationDate());
        itemEntity.setObtentionDate(itemModel.getObtentionDate());
        itemEntity.setCollection(CollectionMapper.mapModelToEntity(itemModel.getCollection()));
        return itemEntity;
    }

    public static ItemModel mapUpdateDtoToModel(ItemUpdateDto itemUpdateDto) {
        ItemModel itemModel = new ItemModel();
        itemModel.setLabel(itemUpdateDto.getLabel());
        itemModel.setObtentionDate(itemUpdateDto.getObtentionDate());
        return itemModel;
    }

    public static ItemDto mapModelToDto(ItemModel itemModel) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemModel.getId());
        itemDto.setLabel(itemModel.getLabel());
        itemDto.setCreationDate(itemModel.getCreationDate());
        itemDto.setObtentionDate(itemModel.getObtentionDate());
        itemDto.setProperties(itemModel.getProperties().stream().map(ItemPropertyMapper::mapModelToDto).toList());
        return itemDto;
    }
}
