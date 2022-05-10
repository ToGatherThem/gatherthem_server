package fr.gatherthem.gatherthem_server.commons.dao;

import fr.gatherthem.gatherthem_server.commons.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<AuthorityEntity, String> {
}
