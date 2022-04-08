package fr.bryanprolong.gatherthem.gatherthem_server.commons.dao;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entitie.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<AuthorityEntity, String> {
}
