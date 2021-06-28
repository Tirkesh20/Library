package dao.implementation;

import exceptions.DaoException;
import dao.Dao;
import entities.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao extends Dao<Author> {


    @Override
    public void delete( ) {
        String sql = "DELETE from Author";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public Author findByName(String name) throws DaoException {
        String query = "select * from Author where name=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2, name);
            ResultSet resultSet = stmt.executeQuery();
            return (resultSet.next()) ? fetchResultSet(resultSet) : null;
        } catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    public List<Author> select() throws DaoException {

        String query = "select * from Author";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            List<Author> accounts = new ArrayList<>();
            while (resultSet.next()) {
                accounts.add(fetchResultSet(resultSet));
            }
            return accounts;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM Author WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public Author create(Author author) throws DaoException {
        String sql = "INSERT INTO Author( id, name) VALUES (?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            fetchSet(stmt, author);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        }
        return author;
    }

    @Override
    public void update(Author author) {
        String sql = "UPDATE Author set name=? where id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            fetchSet(stmt,author);
            stmt.setInt(1, (int) author.getId());
            stmt.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Author readById(long id) throws DaoException {
        String query = "select * from Author where id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            return (resultSet.next()) ? fetchResultSet(resultSet) : null;
        } catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    private Author fetchResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("first_name"));
        return author;
    }
    private void fetchSet(PreparedStatement stmt, Author entity) throws SQLException {
        stmt.setLong(1, entity.getId());
        stmt.setString(2, entity.getName());
    }
}
