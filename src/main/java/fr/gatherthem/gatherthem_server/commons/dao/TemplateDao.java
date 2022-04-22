package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TemplateDao extends JpaRepository<TemplateEntity, UUID> {
    @Query("SELECT t FROM TemplateEntity t WHERE t.owner.id = :userId OR t.visibility = 'PUBLIC'")
    List<TemplateEntity> findAllByUserId(UUID userId);
}
