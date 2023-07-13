package internal.rest;

public class RestAppException extends Exception {
    RestAppException(String message) {
        super(RestAppException.class.getName() + ": " + message);
    }
}
