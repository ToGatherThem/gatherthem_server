package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemDao extends JpaRepository<ItemEntity, UUID> {
    @Query("select item from ItemEntity item where item.collection.id = :collectionId order by item.creationDate desc")
    List<ItemEntity> findByCollectionId(UUID collectionId);

    @Query("select count(item) from ItemEntity item where item.collection.id = :collectionId order by item.creationDate desc")
    int findNumberOfItemsByCollectionId(UUID collectionId);

}
