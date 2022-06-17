package fr.gatherthem.gatherthem_server.template.exposition.controller;

import fr.gatherthem.gatherthem_server.template.domain.service.TemplateService;
import fr.gatherthem.gatherthem_server.template.exposition.dto.TemplateDto;
import fr.gatherthem.gatherthem_server.template.mapper.TemplateMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    /**
     * Gets all templates belonging to the authenticated user or marked as public
     * @return
     *   <p>200 if the templates were retrieved, with the list of the templates</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping()
    public ResponseEntity<List<TemplateDto>> getTemplates() {
        try {
            return ResponseEntity.ok(templateService.findAllForConnectedUser().stream().map(TemplateMapper::mapModelToDto).toList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
