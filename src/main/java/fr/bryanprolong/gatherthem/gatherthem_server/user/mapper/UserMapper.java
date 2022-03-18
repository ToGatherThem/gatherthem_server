package fr.bryanprolong.gatherthem.gatherthem_server.user.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.User;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.dto.UserRegisterDto;

public class UserMapper {
    public static User mapEntityToModel(UserEntity userEntity) {
        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        return user;
    }

    public static UserEntity mapModelToEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        return userEntity;
    }
}
