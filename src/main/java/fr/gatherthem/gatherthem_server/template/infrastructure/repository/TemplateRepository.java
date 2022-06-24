package fr.gatherthem.gatherthem_server.template.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.PropertyDao;
import fr.gatherthem.gatherthem_server.commons.dao.TemplateDao;
import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;
import fr.gatherthem.gatherthem_server.commons.entity.TemplateEntity;
import fr.gatherthem.gatherthem_server.template.domain.model.PropertyModel;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.mapper.PropertyMapper;
import fr.gatherthem.gatherthem_server.template.mapper.TemplateMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TemplateRepository {
    private final TemplateDao templateDao;
    private final PropertyDao propertyDao;

    public TemplateRepository(TemplateDao templateDao, PropertyDao propertyDao) {
        this.templateDao = templateDao;
        this.propertyDao = propertyDao;
    }

    public List<TemplateModel> findAllByUserId(UUID userId) {
        return templateDao.findAllByUserId(userId).stream().map(TemplateMapper::mapEntityToModel).toList();
    }

    public Optional<TemplateModel> findById(UUID id) {
        return templateDao.findById(id).map(TemplateMapper::mapEntityToModel);
    }

    public TemplateModel createTemplate(TemplateModel templateModel) {
        TemplateEntity templateEntity = TemplateMapper.mapModelToEntity(templateModel);
        templateEntity.setProperties(new ArrayList<>());

        if (templateModel.getParent() != null) {
            templateEntity.setParent(templateDao.findById(templateModel.getParent().getId()).orElse(null));
        }

        TemplateEntity templateCreated = templateDao.save(templateEntity);

        for(PropertyModel propertyModel : templateModel.getProperties()) {
            PropertyEntity propertyEntity = PropertyMapper.mapModelToEntity(propertyModel);
            propertyEntity.setTemplate(templateCreated);
            propertyDao.save(propertyEntity);
        }

        return templateDao.findById(templateCreated.getId()).map(TemplateMapper::mapEntityToModel).orElse(null);
    }
}
