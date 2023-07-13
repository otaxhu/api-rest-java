package internal.rest;

import internal.models.Movie;

import internal.service.MovieService;
import internal.service.MovieServiceInternalServerException;
import internal.service.MovieServiceInvalidParamsException;
import internal.service.MovieServiceNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/movies")
public class JerseyMovieController {

    private static MovieService movieService;
    static void setMovieService(MovieService movieService) {
        JerseyMovieController.movieService = movieService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Movie> getMovies(@QueryParam("page") int page) {
        try {
            return movieService.getMovies(page);
        } catch (MovieServiceNotFoundException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.NOT_FOUND);
        } catch (MovieServiceInvalidParamsException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.BAD_REQUEST);
        } catch (MovieServiceInternalServerException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}")
    public Movie getMovieById(@PathParam("id") int id) {
        try {
            return movieService.getMovieById(id);
        } catch (MovieServiceNotFoundException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.NOT_FOUND);
        } catch (MovieServiceInternalServerException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public void postMovie(Movie movie) {
        try {
            movieService.SaveMovie(movie);
        } catch (MovieServiceInternalServerException ex) {
            throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
