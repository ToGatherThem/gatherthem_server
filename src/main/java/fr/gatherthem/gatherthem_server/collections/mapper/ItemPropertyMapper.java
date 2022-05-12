package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.ItemPropertyCreationModel;
import fr.gatherthem.gatherthem_server.collections.domain.model.ItemPropertyModel;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemPropertyCreationDto;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.ItemPropertyDto;
import fr.gatherthem.gatherthem_server.commons.entity.ItemPropertyEntity;

public class ItemPropertyMapper {
    public static ItemPropertyModel mapEntityToModel(ItemPropertyEntity itemPropertyEntity) {
        ItemPropertyModel itemPropertyModel = new ItemPropertyModel();
        itemPropertyModel.setId(itemPropertyEntity.getId());
        itemPropertyModel.setValue(itemPropertyEntity.getValue());
        itemPropertyModel.setProperty(PropertyMapper.mapEntityToModel(itemPropertyEntity.getProperty()));
        return itemPropertyModel;
    }

    public static ItemPropertyDto mapModelToDto(ItemPropertyModel itemPropertyModel) {
        ItemPropertyDto itemPropertyDto = new ItemPropertyDto();
        itemPropertyDto.setId(itemPropertyModel.getId());
        itemPropertyDto.setValue(itemPropertyModel.getValue());
        itemPropertyDto.setProperty(PropertyMapper.mapModelToDto(itemPropertyModel.getProperty()));
        return itemPropertyDto;
    }

    public static ItemPropertyCreationModel mapCreationDtoToCreationModel(ItemPropertyCreationDto itemPropertyCreationDto) {
        ItemPropertyCreationModel itemPropertyCreationModel = new ItemPropertyCreationModel();

        itemPropertyCreationModel.setPropertyId(itemPropertyCreationDto.getPropertyId());
        itemPropertyCreationModel.setValue(itemPropertyCreationDto.getValue());

        return itemPropertyCreationModel;
    }
}
