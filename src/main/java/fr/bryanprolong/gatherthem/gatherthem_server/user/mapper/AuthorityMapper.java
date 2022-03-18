package fr.bryanprolong.gatherthem.gatherthem_server.user.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.AuthorityEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.Authority;

public class AuthorityMapper {
    public static Authority mapEntityToModel(AuthorityEntity authorityEntity) {
        Authority authority = new Authority();

        authority.setCode(authorityEntity.getCode());
        authority.setName(authorityEntity.getName());

        return authority;
    }

    public static AuthorityEntity mapModelToEntity(Authority authority) {
        AuthorityEntity authorityEntity = new AuthorityEntity();

        authorityEntity.setCode(authority.getCode());
        authorityEntity.setName(authority.getName());

        return authorityEntity;
    }
}
