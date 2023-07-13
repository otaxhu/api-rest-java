package internal.service;

import internal.models.Movie;
import internal.repository.MovieRepository;
import internal.repository.RepositoryInternalException;
import internal.repository.RepositoryNoRowsException;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepo;

    public MovieServiceImpl(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public void SaveMovie(Movie movie)
            throws MovieServiceInternalServerException {
        try {
            this.movieRepo.insertMovie(movie);
        } catch (RepositoryInternalException ex) {
            System.err.println(ex.getMessage());
            throw new MovieServiceInternalServerException("internal server error");
        }
    }

    @Override
    public List<Movie> getMovies(int page)
            throws MovieServiceNotFoundException,
            MovieServiceInvalidParamsException,
            MovieServiceInternalServerException {
        if (page <= 0)
            throw new MovieServiceInvalidParamsException("the page param must be greater or equal than 1");
        --page;
        final var limit = 5;
        final var offset = page * limit;
        try {
            return this.movieRepo.getMovies(limit, offset);
        } catch (RepositoryNoRowsException ex) {
            throw new MovieServiceNotFoundException("not found");
        } catch (RepositoryInternalException ex) {
            System.err.println(ex.getMessage());
            throw new MovieServiceInternalServerException("internal server error");
        }
    }

    @Override
    public Movie getMovieById(int id)
            throws MovieServiceNotFoundException,
            MovieServiceInternalServerException {
        try {
            return this.movieRepo.getMovieById(id);
        } catch (RepositoryNoRowsException ex) {
            throw new MovieServiceNotFoundException("not found");
        } catch (RepositoryInternalException ex) {
            System.err.println(ex.getMessage());
            throw new MovieServiceInternalServerException("internal server error");
        }
    }

    @Override
    public void deleteMovie(int id)
            throws MovieServiceNotFoundException,
            MovieServiceInternalServerException {
        try {
            this.movieRepo.deleteMovie(id);
        } catch (RepositoryNoRowsException ex) {
            throw new MovieServiceNotFoundException("not found");
        } catch (RepositoryInternalException ex) {
            System.err.println(ex.getMessage());
            throw new MovieServiceInternalServerException("internal server error");
        }
    }
}
