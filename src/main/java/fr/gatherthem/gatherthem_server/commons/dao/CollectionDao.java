package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CollectionDao extends JpaRepository<CollectionEntity, UUID> {
    @Query("SELECT c FROM CollectionEntity c WHERE c.owner.id = :userId order by c.creationDate desc")
    List<CollectionEntity> findAllByUserId(UUID userId);
}
