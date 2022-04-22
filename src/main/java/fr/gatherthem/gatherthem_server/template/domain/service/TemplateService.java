package fr.gatherthem.gatherthem_server.template.domain.service;

import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.infrastructure.repository.TemplateRepository;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
