package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.PropertyModel;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.PropertyDto;
import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;

public class PropertyMapper {
    public static PropertyModel mapEntityToModel(PropertyEntity propertyEntity) {
        PropertyModel propertyModel = new PropertyModel();

        propertyModel.setId(propertyEntity.getId());
        propertyModel.setName(propertyEntity.getName());
        propertyModel.setType(propertyEntity.getType());

        return propertyModel;
    }

    public static PropertyDto mapModelToDto(PropertyModel propertyModel) {
        PropertyDto propertyDto = new PropertyDto();

        propertyDto.setId(propertyModel.getId());
        propertyDto.setName(propertyModel.getName());
        propertyDto.setType(propertyModel.getType());

        return propertyDto;
    }
}
