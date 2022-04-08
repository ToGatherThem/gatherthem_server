package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemDao extends JpaRepository<ItemEntity, UUID> {
}
