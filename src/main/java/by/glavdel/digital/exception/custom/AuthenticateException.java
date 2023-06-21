package by.glavdel.digital.exception.custom;

public class AuthenticateException extends RuntimeException {

    public AuthenticateException() {
    }

    public AuthenticateException(String message) {
        super(message);
    }
}
