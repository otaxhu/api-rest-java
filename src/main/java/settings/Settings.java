package settings;

import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Settings {

    public final Database database;

    public Settings() throws ConfigurationException {

        var config = new Configurations().properties(new File(".properties"));

        config.setThrowExceptionOnMissing(true);

        this.database = new Database(
                config.getString("DB_USER"),
                config.getString("DB_PASSWORD", ""),
                config.getString("DB_DRIVER"),
                config.getString("DB_HOST"),
                config.getInt("DB_PORT"),
                config.getString("DB_NAME")
        );
    }
    public record Database(
            String user,
            String password,
            String driver,
            String host,
            int    port,
            String name
    ) {}
}
