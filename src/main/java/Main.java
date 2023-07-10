import database.SqlConnection;
import org.apache.commons.configuration2.ex.ConfigurationException;
import settings.Settings;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var settings = new Settings();
            var conn = SqlConnection.getConnection(settings.database);
        }
        catch (ConfigurationException ex) {
            System.err.println("there was an error with the configuration");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        catch (SQLException ex) {
            System.err.println("there was an error with the database");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}
