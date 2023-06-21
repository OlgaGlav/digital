package by.glavdel.digital.exception;

public final class ExceptionMessageConstants {

    public static final String USER_NOT_FOUND = "User not found.";
    public static final String INVALID_TOKEN = "Invalid token.";
    public static final String INVALID_PASSWORD = "Invalid password.";
    public static final String USER_WITH_SUCH_EMAIL_EXIST = "User with such email exist.";
    public static final String USER_WITH_SUCH_USERNAME_EXIST = "User with such username exist.";
    public static final String USER_NOT_REGISTRATE = "User with such email hasn't registration in system.";
    public static final String EMAIL_DONT_MATCH_ID = "User email dont match id.";
    public static final String NOT_VALID_PRICE = "The price should be positive or 0.";
    public static final String NOT_VALID_ROLE = "The role should be ROLE_USER or ROLE_ADMIN or not to be at all (default is ROLE_USER). The ROLE_ADMIN shouldn't be able in future";
    public static final String ERROR_USERNAME_PATTERN = "Should be min 3 symbols not start with space";
    public static final String ERROR_PASSWORD_PATTERN = "Should be min 3 symbols without space";

    public static final String ITEM_NOT_FOUND = "Item with such id doesn't exist in system or was removed.";
    public static final String ORDER_NOT_FOUND = "Order with such id doesn't exist in system or was removed.";
    public static final String POSITION_NOT_FOUND = "Position with such id doesn't exist in system or was removed.";
    public static final String NOT_VALID_COUNT = "The count should be positive or 0.";

    public static final String NOT_ENOUGH_COUNT = "There isn't enough count.";
}
