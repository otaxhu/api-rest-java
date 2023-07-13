package internal.repository;

import internal.models.Movie;

public interface MovieRepository {
    void insertMovie(Movie movie) throws RepositoryException;
}
