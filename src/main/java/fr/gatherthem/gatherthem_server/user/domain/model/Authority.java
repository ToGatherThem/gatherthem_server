package fr.gatherthem.gatherthem_server.user.domain.model;

public class Authority {
    private String code;
    private String name;

    public Authority() {
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
