package fr.gatherthem.gatherthem_server.template.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.gatherthem.gatherthem_server.template.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.template.exposition.dto.UserDto;

public class UserMapper {
    public static UserModel mapEntityToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());

        return userModel;
    }

    public static UserDto mapModelToDto(UserModel userModel) {
        UserDto userDto = new UserDto();

        userDto.setId(userModel.getId());
        userDto.setUsername(userModel.getUsername());
        userDto.setEmail(userModel.getEmail());

        return userDto;
    }
}
