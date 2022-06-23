package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.ItemPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ItemPropertyDao extends JpaRepository<ItemPropertyEntity, UUID> {}
