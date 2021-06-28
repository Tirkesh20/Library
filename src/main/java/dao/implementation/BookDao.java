package dao.implementation;

import exceptions.DaoException;
import dao.Dao;
import entities.Book;
import entities.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao<Book> {

    @Override
    public void delete( ) {
        String sql = "DELETE from Author";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
        }
    }

    public List<Book> select() throws DaoException {

        String query = "select * from Book";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(fetchResultSet(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM Book WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public Book create(Book book) throws DaoException {
        String sql = "INSERT INTO Book( id, name) VALUES (?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            fetchSet(stmt, book);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());

        }
        return book;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE Book set name=? where id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            fetchSet(stmt,book);
            stmt.setInt(1, (int) book.getId());
            stmt.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Book readById(long id) throws DaoException {
        String query = "select * from Book where id=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            return (resultSet.next()) ? fetchResultSet(resultSet) : null;
        } catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    public Book readByName(String bookName) throws DaoException {
        String query = "select * from Book where name=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(2,bookName);
            ResultSet resultSet = stmt.executeQuery();
            return (resultSet.next()) ? fetchResultSet(resultSet) : null;
        } catch (SQLException e) {
            throw  new DaoException(e.getMessage());
        }
    }

    public List<Book> findByGenre(String genre)throws  DaoException{
            String query = "select * from book where genre=?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                ResultSet resultSet = stmt.executeQuery();
                List<Book> accounts = new ArrayList<>();
                while (resultSet.next()) {
                    accounts.add(fetchResultSet(resultSet));
                }
                return accounts;
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
    }

    private Book fetchResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setGangre(Genre.valueOf(resultSet.getString("genre")));
        return book;
    }
    private void fetchSet(PreparedStatement stmt, Book entity) throws SQLException {
        stmt.setLong(1, entity.getId());
        stmt.setString(2, entity.getName());
        stmt.setString(2, entity.getGangre().toString());
    }
}
