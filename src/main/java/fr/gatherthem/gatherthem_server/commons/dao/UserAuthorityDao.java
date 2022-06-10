package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.UserAuthorityEntity;
import fr.gatherthem.gatherthem_server.commons.entity.UserAuthorityEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityDao  extends JpaRepository<UserAuthorityEntity, UserAuthorityEntityPK> {
}
