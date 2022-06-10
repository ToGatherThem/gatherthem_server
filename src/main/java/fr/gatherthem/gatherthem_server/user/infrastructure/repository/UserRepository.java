package fr.gatherthem.gatherthem_server.user.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.AuthorityDao;
import fr.gatherthem.gatherthem_server.commons.dao.UserAuthorityDao;
import fr.gatherthem.gatherthem_server.commons.dao.UserDao;
import fr.gatherthem.gatherthem_server.commons.entity.AuthorityEntity;
import fr.gatherthem.gatherthem_server.commons.entity.UserAuthorityEntity;
import fr.gatherthem.gatherthem_server.commons.entity.UserAuthorityEntityPK;
import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.gatherthem.gatherthem_server.user.domain.model.Authority;
import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.user.mapper.AuthorityMapper;
import fr.gatherthem.gatherthem_server.user.mapper.UserMapper;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository {
    private final UserDao userDao;
    private final UserAuthorityDao userAuthorityDao;
    private final AuthorityDao authorityDao;

    public UserRepository(UserDao userDao, UserAuthorityDao userAuthorityDao, AuthorityDao authorityDao) {
        this.userDao = userDao;
        this.userAuthorityDao = userAuthorityDao;
        this.authorityDao = authorityDao;
    }

    public Optional<UserModel> findByUsername(String username) {
        return userDao.findByUsername(username).map(UserMapper::mapEntityToModel);
    }

    public Optional<UserModel> findByEmail(String email) {
        return userDao.findByEmail(email).map(UserMapper::mapEntityToModel);
    }

    public void create(String username, String email, String encryptPassword) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(encryptPassword);

        userDao.save(userEntity);
    }

    public int nbCollectionsByUserId(UUID userId) {
        return userDao.countCollectionsByUserId(userId);
    }

    public int nbItemsByUserId(UUID userId) {
        return userDao.countItemsByUserId(userId);
    }

    public UserModel addAuthority(UUID userId, String authorityCode) {
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();
        UserAuthorityEntityPK userAuthorityEntityPK = new UserAuthorityEntityPK();
        UserEntity userEntity = userDao.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userAuthorityEntityPK.setUser(userEntity);
        AuthorityEntity authorityEntity = authorityDao.findById(authorityCode).orElseThrow(() -> new IllegalArgumentException("Authority not found"));
        userAuthorityEntityPK.setAuthority(authorityEntity);
        userAuthorityEntity.setId(userAuthorityEntityPK);
        userAuthorityDao.save(userAuthorityEntity);
        return userDao.findById(userId).map(UserMapper::mapEntityToModel).orElse(null);
    }

}
