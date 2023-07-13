package internal.service;

public class MovieServiceInvalidParamsException extends Exception {
    MovieServiceInvalidParamsException(String message) {
        super(message);
    }
}
