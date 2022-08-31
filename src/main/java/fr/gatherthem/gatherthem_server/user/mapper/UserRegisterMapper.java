package fr.gatherthem.gatherthem_server.user.mapper;

import fr.gatherthem.gatherthem_server.user.domain.model.UserRegister;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserRegisterDto;

public class UserRegisterMapper {
    public static UserRegister mapDtoToModel(UserRegisterDto userRegisterDto) {
        UserRegister userRegister = new UserRegister();

        userRegister.setUsername(userRegisterDto.getUsername());
        userRegister.setEmail(userRegisterDto.getEmail());
        userRegister.setPassword(userRegisterDto.getPassword());
        userRegister.setCode(userRegisterDto.getCode());

        return userRegister;
    }
}
