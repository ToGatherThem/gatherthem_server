package fr.gatherthem.gatherthem_server.template.exposition.controller;

import fr.gatherthem.gatherthem_server.commons.exception.ForbiddenException;
import fr.gatherthem.gatherthem_server.template.domain.model.TemplateModel;
import fr.gatherthem.gatherthem_server.template.domain.service.TemplateService;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateCreationDto;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateDto;
import fr.gatherthem.gatherthem_server.template.mapper.TemplateMapper;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping()
    public ResponseEntity<List<TemplateDto>> getTemplates() {
        try {
            return ResponseEntity.ok(templateService.findAllForConnectedUser().stream().map(TemplateMapper::mapModelToDto).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping()
    public ResponseEntity<TemplateDto> createTemplate(@RequestBody TemplateCreationDto templateCreationDto) {
        if(templateCreationDto.isValid()) {
            try {
                TemplateModel createdTemplate = templateService.createTemplate(TemplateMapper.mapCreationDtoToCreationModel(templateCreationDto));
                return ResponseEntity.created(URI.create("/templates/" + createdTemplate.getId())).body(TemplateMapper.mapModelToDto(createdTemplate));
            } catch (ForbiddenException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
