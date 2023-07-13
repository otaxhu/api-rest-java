import internal.repository.MovieRepositoryFactory;
import internal.repository.RepositoryException;
import internal.rest.RestAppException;
import internal.rest.RestAppFactory;
import org.apache.commons.configuration2.ex.ConfigurationException;
import settings.Settings;

public class Main {
    public static void main(String[] args) {
        try {
            var serverSettings = new Settings();
            var restApp = RestAppFactory.create(serverSettings.server);
            restApp.start();
        } catch (ConfigurationException | RestAppException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
