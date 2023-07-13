package internal.repository;

public class RepositoryException extends Exception {
    RepositoryException(String message) {
        super(RepositoryException.class.getName() + ": " + message);
    }
}
