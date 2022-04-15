package fr.gatherthem.gatherthem_server.commons.entity;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @Column(name = "authority_code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    public AuthorityEntity() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
