package fr.gatherthem.gatherthem_server.template.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.TemplateEntity;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateCreationModel;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateCreationDto;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateDto;

import java.util.ArrayList;

public class TemplateMapper {
    public static TemplateModel mapEntityToModel(TemplateEntity templateEntity) {
        TemplateModel templateModel = new TemplateModel();

        templateModel.setId(templateEntity.getId());
        templateModel.setName(templateEntity.getName());
        templateModel.setDescription(templateEntity.getDescription());
        templateModel.setItemLabelName(templateEntity.getItemLabelName());
        templateModel.setVisibility(templateEntity.getVisibility());
        if(templateEntity.getParent() != null) templateModel.setParent(TemplateMapper.mapEntityToModel(templateEntity.getParent()));
        templateModel.setOwner(UserMapper.mapEntityToModel(templateEntity.getOwner()));
        templateModel.setProperties(templateEntity.getProperties().stream().map(PropertyMapper::mapEntityToModel).toList());

        return templateModel;
    }

    public static TemplateDto mapModelToDto(TemplateModel templateModel) {
        TemplateDto templateDto = new TemplateDto();

        templateDto.setId(templateModel.getId());
        templateDto.setName(templateModel.getName());
        templateDto.setDescription(templateModel.getDescription());
        templateDto.setItemLabelName(templateModel.getItemLabelName());
        templateDto.setVisibility(templateModel.getVisibility());
        if(templateModel.getParent() != null) templateDto.setParent(TemplateMapper.mapModelToDto(templateModel.getParent()));
        templateDto.setOwner(UserMapper.mapModelToDto(templateModel.getOwner()));
        templateDto.setProperties(templateModel.getProperties().stream().map(PropertyMapper::mapModelToDto).toList());

        return templateDto;
    }

    public static TemplateCreationModel mapCreationDtoToCreationModel(TemplateCreationDto templateCreationDto) {
        TemplateCreationModel templateCreationModel = new TemplateCreationModel();

        templateCreationModel.setName(templateCreationDto.getName());
        templateCreationModel.setDescription(templateCreationDto.getDescription());
        templateCreationModel.setItemLabelName(templateCreationDto.getItemLabelName());
        templateCreationModel.setVisibility(templateCreationDto.getVisibility());
        templateCreationModel.setParentId(templateCreationDto.getParentId());
        templateCreationModel.setProperties(templateCreationDto.getProperties().stream().map(PropertyMapper::mapCreationDtoToModel).toList());

        return templateCreationModel;
    }

    public static TemplateEntity mapModelToEntity(TemplateModel templateModel) {
        TemplateEntity templateEntity = new TemplateEntity();

        templateEntity.setName(templateModel.getName());
        templateEntity.setDescription(templateModel.getDescription());
        templateEntity.setItemLabelName(templateModel.getItemLabelName());
        templateEntity.setVisibility(templateModel.getVisibility());
        if(templateModel.getParent() != null) templateEntity.setParent(TemplateMapper.mapModelToEntity(templateModel.getParent()));
        templateEntity.setOwner(UserMapper.mapModelToEntity(templateModel.getOwner()));
        templateEntity.setProperties( (templateModel.getProperties() != null) ? templateModel.getProperties().stream().map(PropertyMapper::mapModelToEntity).toList() : new ArrayList<>());

        return templateEntity;
    }
}
