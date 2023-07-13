package internal.service;

import internal.models.Movie;

import java.util.List;

public interface MovieService {
    void SaveMovie(Movie movie)
            throws MovieServiceInternalServerException;
    List<Movie> getMovies(int page)
            throws MovieServiceNotFoundException,
            MovieServiceInternalServerException,
            MovieServiceInvalidParamsException;
    Movie getMovieById(int id)
            throws MovieServiceNotFoundException,
            MovieServiceInternalServerException;
    void deleteMovie(int id)
            throws MovieServiceNotFoundException,
            MovieServiceInternalServerException;
}
