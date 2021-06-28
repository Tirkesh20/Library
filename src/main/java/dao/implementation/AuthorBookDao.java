package dao.implementation;

import exceptions.DaoException;
import dao.Dao;
import entities.Author;
import entities.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorBookDao extends Dao{

    public void insert(Author author,Book book) throws DaoException {
        String sql = "INSERT INTO \"m2m\"( author_id, book_id) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, author.getId());
            stmt.setLong(2, book.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public long findAuthorId(long id) throws DaoException {
            String query = "select * from m2m where book_id=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(2, id);
                ResultSet resultSet = stmt.executeQuery();
              if (resultSet.next()) {
                  return fetchResultSetAuthor(resultSet);
              }
            } catch (SQLException e) {
                throw  new DaoException(e.getMessage());
            }
            return 0;
    }



    public long findAuthorsBookId(long id) throws DaoException {
        String query = "select * from m2m where author_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(2, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return fetchResultSetBook(resultSet);
            }
        } catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
        return 0;
    }

    private long fetchResultSetBook(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("id");
    }
    private long fetchResultSetAuthor(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("id");
    }


}
