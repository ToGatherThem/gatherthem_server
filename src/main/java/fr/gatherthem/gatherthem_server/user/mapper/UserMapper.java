package fr.gatherthem.gatherthem_server.user.mapper;

import fr.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserDto;

public class UserMapper {
    public static UserModel mapEntityToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();

        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setAuthorities(userEntity.getAuthorities().stream().map(AuthorityMapper::mapEntityToModel).toList());

        return userModel;
    }

    public static UserEntity mapModelToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userModel.getId());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setAuthorities(userModel.getAuthorities().stream().map(AuthorityMapper::mapModelToEntity).toList());

        return userEntity;
    }

    public static UserDto mapAppUserToUserDto(AppUser appUser) {
        UserDto userDto = new UserDto();

        userDto.setId(appUser.getId());
        userDto.setUsername(appUser.getUsername());
        userDto.setEmail(appUser.getEmail());
        userDto.setAuthorities(appUser.getAuthorityList().stream().map(AuthorityMapper::mapModelToDto).toList());

        return userDto;
    }

    public  static  UserModel mapAppUserToUserModel(AppUser appUser){
        UserModel userModel = new UserModel();

        userModel.setId(appUser.getId());
        userModel.setUsername(appUser.getUsername());
        userModel.setEmail(appUser.getEmail());
        userModel.setAuthorities(appUser.getAuthorityList());
        return userModel;
    }
}
