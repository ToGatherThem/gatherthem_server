package fr.gatherthem.gatherthem_server.template.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;
import fr.gatherthem.gatherthem_server.template.domain.model.PropertyModel;
import fr.gatherthem.gatherthem_server.template.exposition.dto.PropertyCreationDto;
import fr.gatherthem.gatherthem_server.template.exposition.dto.PropertyDto;

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

    public static PropertyModel mapCreationDtoToModel(PropertyCreationDto propertyCreationDto) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setName(propertyCreationDto.getName());
        propertyModel.setType(propertyCreationDto.getType());
        return propertyModel;
    }

    public static PropertyEntity mapModelToEntity(PropertyModel propertyModel) {
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setName(propertyModel.getName());
        propertyEntity.setType(propertyModel.getType());
        return propertyEntity;
    }
}
