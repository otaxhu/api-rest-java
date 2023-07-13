package internal.rest;

import settings.Settings.Server;

public class RestAppFactory {
    private RestAppFactory(){}

    public static RestApp create(Server serverSettings) throws RestAppException {
        return switch (serverSettings.framework()) {
            case "jersey" -> new JerseyRestApp(serverSettings);

            default -> throw new RestAppException(
                    "the \"%s\" framework does not have a RestApp implementation"
                            .formatted(serverSettings.framework())
            );
        };
    }
}
