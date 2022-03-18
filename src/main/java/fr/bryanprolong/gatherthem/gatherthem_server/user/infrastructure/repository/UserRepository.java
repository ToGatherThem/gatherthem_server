package fr.bryanprolong.gatherthem.gatherthem_server.user.infrastructure.repository;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.dao.UserDao;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.User;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserRegister;
import fr.bryanprolong.gatherthem.gatherthem_server.user.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository {
    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findUserByUsername(String username) {
        return userDao.findByUsername(username).map(UserMapper::mapEntityToModel);
    }

    public Optional<User> findUserByEmail(String email) {
        return userDao.findByEmail(email).map(UserMapper::mapEntityToModel);
    }

    public void create(String username, String email, String encryptPassword) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(encryptPassword);

        userDao.save(userEntity);
    }
}
