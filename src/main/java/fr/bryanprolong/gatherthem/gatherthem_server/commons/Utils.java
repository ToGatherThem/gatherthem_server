package fr.bryanprolong.gatherthem.gatherthem_server.commons;

import java.util.regex.Pattern;

public class Utils {
    private static final Pattern emailRegexPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public static boolean isValidEmail(String email) {
        return email != null && emailRegexPattern.matcher(email).matches() && email.length() <= 320;
    }
}
