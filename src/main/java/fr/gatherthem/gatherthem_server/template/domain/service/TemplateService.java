package fr.gatherthem.gatherthem_server.template.domain.service;

import fr.gatherthem.gatherthem_server.commons.exception.ForbiddenException;
import fr.gatherthem.gatherthem_server.template.domain.model.PropertyModel;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateCreationModel;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.infrastructure.repository.TemplateRepository;
import fr.gatherthem.gatherthem_server.template.mapper.UserMapper;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemplateService {
    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<TemplateModel> findAllForConnectedUser() {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return templateRepository.findAllByUserId(user.getId());
    }

    public TemplateModel createTemplate(TemplateCreationModel templateCreationModel) throws ForbiddenException {
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = user.getAuthorityList().stream().anyMatch(a -> a.getCode().equals("ADMIN"));

        if(templateCreationModel.getVisibility().equals("PUBLIC") && !isAdmin) {
            throw new ForbiddenException();
        } else {
            TemplateModel templateModel = new TemplateModel();
            templateModel.setName(templateCreationModel.getName());
            templateModel.setDescription(templateCreationModel.getDescription());
            templateModel.setItemLabelName(templateCreationModel.getItemLabelName());
            templateModel.setVisibility(templateCreationModel.getVisibility());

            if(templateCreationModel.getParentId() != null) {
                Optional<TemplateModel> optionalParentTemplate = templateRepository.findById(templateCreationModel.getParentId());
                optionalParentTemplate.ifPresent(templateModel::setParent);

                if(templateModel.getVisibility().equals("PUBLIC") && !templateModel.getParent().isAllPublic()) {
                    throw new ForbiddenException();
                }
            }

            templateModel.setOwner(UserMapper.mapAppUserToUserModel(user));
            templateModel.setProperties(templateCreationModel.getProperties());

            return templateRepository.createTemplate(templateModel);
        }
    }
}
