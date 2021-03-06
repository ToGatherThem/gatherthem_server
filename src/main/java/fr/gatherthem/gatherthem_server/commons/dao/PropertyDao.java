package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyDao extends JpaRepository<PropertyEntity, UUID> {
}
