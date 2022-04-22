package fr.gatherthem.gatherthem_server.template.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.TemplateEntity;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateDto;

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
}
