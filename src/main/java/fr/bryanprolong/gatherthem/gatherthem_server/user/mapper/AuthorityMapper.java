package fr.bryanprolong.gatherthem.gatherthem_server.user.mapper;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.entitie.AuthorityEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.Authority;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.dto.AuthorityDto;

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

    public static AuthorityDto mapModelToDto(Authority authority) {
        AuthorityDto authorityDto = new AuthorityDto();

        authorityDto.setCode(authority.getCode());
        authorityDto.setName(authority.getName());

        return authorityDto;
    }
}
