package fr.gatherthem.gatherthem_server.commons.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_authority")
public class UserAuthorityEntity {

    @EmbeddedId
    private UserAuthorityEntityPK id;

    public UserAuthorityEntity() {
    }

    public UserAuthorityEntityPK getId() {
        return id;
    }

    public void setId(UserAuthorityEntityPK id) {
        this.id = id;
    }
}
