package fr.gatherthem.gatherthem_server.user.domain.model;

import java.util.Date;
import java.util.Random;

public class EmailVerifyModel {
    private final String email;
    private final String code;
    private final Date expirationDate;

    public EmailVerifyModel(String email) {
        this.email = email;

        Random rnd = new Random();
        StringBuilder codeGenerated;
        codeGenerated = new StringBuilder(Integer.toString(rnd.nextInt(999999)));
        while (codeGenerated.length() < 6) {
            codeGenerated.insert(0, "0");
        }
        this.code = codeGenerated.toString();

        this.expirationDate = new Date(new Date().getTime() + 2 * 60 * 60 * 1000);
    }

    public String getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
