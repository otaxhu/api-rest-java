package internal.repository;

import internal.models.Movie;

import java.util.List;

public interface MovieRepository {

    void insertMovie(Movie movie)
            throws RepositoryInternalException;

    Movie getMovieById(int id)
            throws RepositoryInternalException,
            RepositoryNoRowsException;

    List<Movie> getMovies(int limit, int offset)
            throws RepositoryInternalException,
            RepositoryNoRowsException;

    void deleteMovie(int id)
            throws RepositoryInternalException,
            RepositoryNoRowsException;
}
