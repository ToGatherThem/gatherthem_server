package fr.gatherthem.gatherthem_server.collections.mapper;

import fr.gatherthem.gatherthem_server.collections.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.collections.exposition.dto.UserDto;
import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;

public class UserMapper {
    public static UserModel mapEntityToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());

        return userModel;
    }

    public static UserEntity mapModelToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userModel.getId());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setEmail(userModel.getEmail());

        return userEntity;
    }

    public static UserDto mapModelToDto(UserModel userModel) {
        UserDto userDto = new UserDto();

        userDto.setId(userModel.getId());
        userDto.setUsername(userModel.getUsername());
        userDto.setEmail(userModel.getEmail());

        return userDto;
    }

    public static UserModel mapAppUserToUserModel(AppUser appUser) {
        UserModel userModel = new UserModel();

        userModel.setId(appUser.getId());
        userModel.setUsername(appUser.getUsername());
        userModel.setEmail(appUser.getEmail());

        return userModel;
    }
}
