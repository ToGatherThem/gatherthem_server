package fr.gatherthem.gatherthem_server.items.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.ItemPropertyEntity;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemPropertyModel;
import fr.gatherthem.gatherthem_server.items.domain.model.ItemPropertyUpdateModel;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemPropertyDto;
import fr.gatherthem.gatherthem_server.items.exposition.dto.ItemPropertyUpdateDto;

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

    public static ItemPropertyUpdateModel mapDtoToUpdateModel(ItemPropertyUpdateDto itemPropertyUpdateDto) {
        ItemPropertyUpdateModel itemPropertyUpdateModel = new ItemPropertyUpdateModel();
        itemPropertyUpdateModel.setId(itemPropertyUpdateDto.getId());
        itemPropertyUpdateModel.setPropertyId(itemPropertyUpdateDto.getPropertyId());
        itemPropertyUpdateModel.setValue(itemPropertyUpdateDto.getValue());
        return itemPropertyUpdateModel;
    }
}
