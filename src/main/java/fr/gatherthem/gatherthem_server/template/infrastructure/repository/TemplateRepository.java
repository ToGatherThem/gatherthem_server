package fr.gatherthem.gatherthem_server.template.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.TemplateDao;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.mapper.TemplateMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TemplateRepository {
    private final TemplateDao templateDao;

    public TemplateRepository(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    public List<TemplateModel> findAllByUserId(UUID userId) {
        return templateDao.findAllByUserId(userId).stream().map(TemplateMapper::mapEntityToModel).toList();
    }
}
