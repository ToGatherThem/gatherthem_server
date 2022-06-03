package fr.gatherthem.gatherthem_server.user.infrastructure.repository;

import fr.gatherthem.gatherthem_server.commons.dao.UserDao;
import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.user.mapper.UserMapper;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository {
    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
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
}
