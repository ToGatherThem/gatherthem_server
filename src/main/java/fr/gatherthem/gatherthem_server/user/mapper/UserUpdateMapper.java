package fr.gatherthem.gatherthem_server.user.mapper;

import fr.gatherthem.gatherthem_server.user.domain.model.UserUpdateModel;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserUpdateDto;

public class UserUpdateMapper {

    public static UserUpdateModel mapDtoToModel(UserUpdateDto userUpdateDto) {
        UserUpdateModel userUpdateModel = new UserUpdateModel();
        userUpdateModel.setUsername(userUpdateDto.getUsername());
        userUpdateModel.setEmail(userUpdateDto.getEmail());
        userUpdateModel.setPassword(userUpdateDto.getPassword());
        userUpdateModel.setNewPassword(userUpdateDto.getNewPassword());
        userUpdateModel.setNewPasswordConfirm(userUpdateDto.getNewPasswordConfirm());
        return userUpdateModel;
    }
}
