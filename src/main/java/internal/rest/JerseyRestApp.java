package internal.rest;

import jakarta.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import settings.Settings.Server;

class JerseyRestApp implements RestApp{
    private final Server serverSettings;
    private ResourceConfig routes;

    JerseyRestApp(Server serverSettings) throws RestAppException {
        this.serverSettings = serverSettings;
        this.bindRoutes();
    }

    @Override
    public void start() throws RestAppException {
        var uri = UriBuilder.fromUri("http://localhost/").port(this.serverSettings.port()).build();
        JdkHttpServerFactory.createHttpServer(uri, this.routes);
    }

    @Override
    public void bindRoutes() throws RestAppException {
        this.routes = new ResourceConfig(
                JerseyMovieController.class
        );
    }
}
