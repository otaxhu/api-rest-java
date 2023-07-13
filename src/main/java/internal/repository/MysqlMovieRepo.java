package internal.repository;

import internal.models.Movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

class MysqlMovieRepo implements MovieRepository {
    private final Connection db;

    MysqlMovieRepo(Connection db){
        this.db = db;
    }

    @Override
    public void insertMovie(Movie movie) throws RepositoryException {
        try {
            var stmt = this.db.prepareStatement("INSERT INTO movies (title, date, cover_url) VALUES (?, ?, ?)");
            stmt.setString(1, movie.title);
            stmt.setDate(2, Date.valueOf(movie.date));
            stmt.setString(3, movie.coverUrl);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RepositoryException(ex.getMessage());
        }
    }
}
