package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectionDao extends JpaRepository<CollectionEntity, UUID> {
}
