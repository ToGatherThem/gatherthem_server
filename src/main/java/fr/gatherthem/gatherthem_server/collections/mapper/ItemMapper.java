package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.ItemModel;

import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemCreationDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemDto;
import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;

import java.util.Arrays;

public class ItemMapper {
    public static ItemModel mapEntityToModel(ItemEntity itemEntity) {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemEntity.getId());
        itemModel.setLabel(itemEntity.getLabel());
        itemModel.setImage(itemEntity.getImage());
        itemModel.setCreationDate(itemEntity.getCreationDate());
        itemModel.setObtentionDate(itemEntity.getObtentionDate());
        itemModel.setProperties(itemEntity.getProperties().stream().map(ItemPropertyMapper::mapEntityToModel).toList());

        return itemModel;
    }

    public static ItemEntity mapModelToEntity(ItemModel itemModel) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemModel.getId());
        itemEntity.setLabel(itemModel.getLabel());
        itemEntity.setImage(itemModel.getImage());
        itemEntity.setCreationDate(itemModel.getCreationDate());
        itemEntity.setObtentionDate(itemModel.getObtentionDate());
        itemEntity.setCollection(CollectionMapper.mapModelToEntity(itemModel.getCollection()));
        return itemEntity;
    }

    public static ItemDto mapModelToDto(ItemModel itemModel) {
        ItemDto itemDto = new ItemDto();

        itemDto.setId(itemModel.getId());
        itemDto.setLabel(itemModel.getLabel());
        itemDto.setImage(itemModel.getImage());
        itemDto.setCreationDate(itemModel.getCreationDate());
        itemDto.setObtentionDate(itemModel.getObtentionDate());
        itemDto.setProperties(itemModel.getProperties().stream().map(ItemPropertyMapper::mapModelToDto).toList());

        return itemDto;
    }

    public static ItemModel mapCreationDtoToCreationModel(ItemCreationDto itemDto) {
        ItemModel itemModel = new ItemModel();
        itemModel.setLabel(itemDto.getLabel());
        itemModel.setImage(itemDto.getImage());
        itemModel.setObtentionDate(itemDto.getObtentionDate());
        return itemModel;
    }
}
