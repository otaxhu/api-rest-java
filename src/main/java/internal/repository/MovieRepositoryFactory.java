package internal.repository;

import database.SqlConnection;
import settings.Settings.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class MovieRepositoryFactory {
    private MovieRepositoryFactory(){}

    public static MovieRepository create(Database dbSettings) throws MovieRepositoryFactoryException {
        switch (dbSettings.driver()) {
            case "mysql" -> {
                Connection conn;
                try {
                    conn = SqlConnection.getConnection(dbSettings);
                } catch (SQLException ex) {
                    throw new MovieRepositoryFactoryException(ex.getMessage());
                }
                return new MysqlMovieRepo(conn);
            }
            default -> throw new MovieRepositoryFactoryException(
                    "the \"%s\" driver does not have a movie repository implementation"
                            .formatted(dbSettings.driver())
            );
        }
    }
}
