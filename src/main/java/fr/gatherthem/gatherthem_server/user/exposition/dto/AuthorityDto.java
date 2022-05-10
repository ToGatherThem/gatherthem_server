package fr.gatherthem.gatherthem_server.user.exposition.dto;

public class AuthorityDto {
    private String code;
    private String name;

    public AuthorityDto() {
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
