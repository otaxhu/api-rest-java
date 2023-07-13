package internal.rest;

import internal.models.Movie;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;

@Path("/hello")
public class JerseyMovieController {

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Movie getMovie() {
        return new Movie("forrest", LocalDate.now(), "cover url 1234", 1);
    }
}
