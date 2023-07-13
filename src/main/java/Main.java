import internal.repository.MovieRepositoryFactory;
import internal.repository.MovieRepositoryFactoryException;
import internal.repository.RepositoryInternalException;
import internal.rest.RestAppException;
import internal.rest.RestAppFactory;
import internal.rest.RestAppFactoryException;
import internal.service.MovieServiceImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import settings.Settings;

public class Main {
    public static void main(String[] args) {
        try {
            var settings = new Settings();
            var movieRepo = MovieRepositoryFactory.create(settings.database);
            var movieService = new MovieServiceImpl(movieRepo);
            var restApp = RestAppFactory.create(settings.server, movieService);
            restApp.start();
        } catch (ConfigurationException |
                 RestAppException |
                 MovieRepositoryFactoryException |
                 RestAppFactoryException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
