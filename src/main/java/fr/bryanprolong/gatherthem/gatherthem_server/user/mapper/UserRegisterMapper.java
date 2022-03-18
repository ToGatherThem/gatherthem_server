package fr.bryanprolong.gatherthem.gatherthem_server.user.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserRegister;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.dto.UserRegisterDto;

public class UserRegisterMapper {
    public static UserRegister mapDtoToModel(UserRegisterDto userRegisterDto) {
        UserRegister userRegister = new UserRegister();

        userRegister.setUsername(userRegisterDto.getUsername());
        userRegister.setEmail(userRegisterDto.getEmail());
        userRegister.setPassword(userRegisterDto.getPassword());

        return userRegister;
    }
}
