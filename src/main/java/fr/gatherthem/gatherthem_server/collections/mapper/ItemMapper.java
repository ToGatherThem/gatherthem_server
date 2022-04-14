package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;

import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemCreationAndUpdateDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;

public class ItemMapper {
    public static ItemModel mapEntityToModel(ItemEntity itemEntity) {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemEntity.getId());
        itemModel.setLabel(itemEntity.getLabel());
        itemModel.setCreationDate(itemEntity.getCreationDate());
        itemModel.setObtentionDate(itemEntity.getObtentionDate());

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

    public static ItemDto mapModelToDto(ItemModel itemModel) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemModel.getId());
        itemDto.setLabel(itemModel.getLabel());
        itemDto.setCreationDate(itemModel.getCreationDate());
        itemDto.setObtentionDate(itemModel.getObtentionDate());

        return itemDto;
    }

    public static ItemModel mapCreationAndUpdateDtoToModel(ItemCreationAndUpdateDto itemDto) {
        ItemModel itemModel = new ItemModel();
        itemModel.setLabel(itemDto.getLabel());
        itemModel.setObtentionDate(itemDto.getObtentionDate());
        return itemModel;
    }
}
