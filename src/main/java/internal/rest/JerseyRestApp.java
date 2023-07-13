package internal.rest;

import internal.service.MovieService;
import jakarta.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import settings.Settings.Server;

class JerseyRestApp implements RestApp {
    private final Server serverSettings;
    private ResourceConfig resources;

    JerseyRestApp(Server serverSettings, MovieService movieService) throws RestAppException {
        this.serverSettings = serverSettings;
        JerseyMovieController.setMovieService(movieService);
        this.bindRoutes();
    }

    @Override
    public void start() throws RestAppException {
        var uri = UriBuilder.fromUri("http://localhost/").port(this.serverSettings.port()).build();
        JdkHttpServerFactory.createHttpServer(uri, this.resources);
        System.out.printf("server HTTP listening in the %s URL\n", uri);
    }

    @Override
    public void bindRoutes() throws RestAppException {
        this.resources = new ResourceConfig(
                JerseyMovieController.class
        );
    }
}
