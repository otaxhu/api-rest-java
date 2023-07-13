package internal.repository;

import internal.models.Movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class MysqlMovieRepo implements MovieRepository {
    private final Connection db;

    MysqlMovieRepo(Connection db){
        this.db = db;
    }

    @Override
    public void insertMovie(Movie movie) throws RepositoryInternalException {
        try {
            var stmt = this.db.prepareStatement("INSERT INTO movies (title, date, cover_url) VALUES (?, ?, ?)");
            stmt.setString(1, movie.title);
            stmt.setDate(2, Date.valueOf(movie.date));
            stmt.setString(3, movie.coverUrl);
            stmt.execute();
        } catch (SQLException ex) {
            throw new RepositoryInternalException(ex.getMessage());
        }
    }

    @Override
    public Movie getMovieById(int id) throws RepositoryInternalException, RepositoryNoRowsException {
        Movie movie = null;
        try {
            var stmt = this.db.prepareStatement("SELECT title, date, cover_url FROM movies WHERE id = ?");
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                movie = new Movie(
                        resultSet.getString("title"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("cover_url"),
                        id
                );
            }
            if (movie == null)
                throw new RepositoryNoRowsException(
                        "no movie were found with the %d id".formatted(id)
                );
            return movie;
        } catch (SQLException ex) {
            throw new RepositoryInternalException(ex.getMessage());
        }
    }

    @Override
    public List<Movie> getMovies(int limit, int offset) throws RepositoryInternalException, RepositoryNoRowsException {
        List<Movie> movies = new ArrayList<>();
        try {
            var stmt = this.db.prepareStatement("SELECT id, title, date, cover_url FROM movies LIMIT ? OFFSET ?");
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getString("title"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("cover_url"),
                        resultSet.getInt("id")
                ));
            }
            if (movies.isEmpty())
                throw new RepositoryNoRowsException("no entries in the \"movies\" table");
        } catch (SQLException ex) {
            throw new RepositoryInternalException(ex.getMessage());
        }
        return movies;
    }

    @Override
    public void deleteMovie(int id) throws RepositoryInternalException, RepositoryNoRowsException {
        try {
            var stmt = this.db.prepareStatement("DELETE FROM movies WHERE id = ?");
            stmt.setInt(1, id);
            if (stmt.execute())
                throw new RepositoryNoRowsException(
                        "no movies to delete with the %d id".formatted(id)
                );
        } catch (SQLException ex) {
            throw new RepositoryInternalException(ex.getMessage());
        }
    }


}
