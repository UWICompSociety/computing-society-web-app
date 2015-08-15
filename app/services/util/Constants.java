package services.util;

/**
 * Created by shane on 8/13/15.
 */
public class Constants {

    private Constants() {
        throw new AssertionError();
    }

    public static final String EMPTY_STRING = "";

    public static final String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "token";

    public static final String KEY_REG_NO = "registration_number";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_EMAIL = "email";
}
