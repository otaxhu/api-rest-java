package internal.rest;

import internal.service.MovieService;
import settings.Settings.Server;

public class RestAppFactory {
    private RestAppFactory(){}

    public static RestApp create(Server serverSettings, MovieService movieService) throws RestAppFactoryException {
        switch (serverSettings.framework()) {
            case "jersey" -> {
                try {
                    return new JerseyRestApp(serverSettings, movieService);
                } catch (RestAppException ex) {
                    throw new RestAppFactoryException(ex.getMessage());
                }
            }

            default -> throw new RestAppFactoryException(
                    "the \"%s\" framework does not have a RestApp implementation"
                            .formatted(serverSettings.framework())
            );
        }
    }
}
