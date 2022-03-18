package fr.bryanprolong.gatherthem.gatherthem_server.commons.dao;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public interface UserDao  extends JpaRepository<UserEntity, UUID> {
    UserEntity findByUsername(String username);
}
