package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public interface UserDao  extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query("select count(c) from CollectionEntity c where c.owner.id = :userId")
    int countCollectionsByUserId(UUID userId);

    @Query("select count(i) from ItemEntity i inner join i.collection c where c.owner.id = :userId")
    int countItemsByUserId(UUID userId);
}
