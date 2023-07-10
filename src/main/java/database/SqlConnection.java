package database;

import settings.Settings.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

    private SqlConnection(){}

    public static Connection getConnection(Database dbSettings) throws SQLException {
        return DriverManager.getConnection(
                "jdbc:%s://%s:%d/%s"
                .formatted(
                        dbSettings.driver(),
                        dbSettings.host(),
                        dbSettings.port(),
                        dbSettings.name()
                ),
                dbSettings.user(),
                dbSettings.password()
        );
    }
}
