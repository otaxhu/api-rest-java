package internal.repository;

public class RepositoryNoRowsException extends Exception {
    RepositoryNoRowsException(String message) {
        super(message);
    }
}
