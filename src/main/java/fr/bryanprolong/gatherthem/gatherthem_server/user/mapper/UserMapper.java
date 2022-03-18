package fr.bryanprolong.gatherthem.gatherthem_server.user.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.User;

public class UserMapper {
    public static User mapEntityToModel(UserEntity userEntity) {
        User user = new User();

        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setAuthorities(userEntity.getAuthorities().stream().map(AuthorityMapper::mapEntityToModel).toList());

        return user;
    }

    public static UserEntity mapModelToEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setAuthorities(user.getAuthorities().stream().map(AuthorityMapper::mapModelToEntity).toList());

        return userEntity;
    }
}
