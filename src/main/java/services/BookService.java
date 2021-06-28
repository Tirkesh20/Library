package services;

import exceptions.DaoException;
import exceptions.ServiceException;
import dao.implementation.BookDao;
import entities.Author;
import entities.Book;

import java.util.List;

public class BookService {
    private final BookDao bookDao=new BookDao();
    private final AuthorBookConnectionService service=new AuthorBookConnectionService();
    private final AuthorService service2=new AuthorService();


    public Book findBooks(String bookName) throws ServiceException {
        try {
            return bookDao.readByName(bookName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Book> findByGenre(String string) throws ServiceException {
        try {
            bookDao.findByGenre(string);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        return null;
    }

    public boolean deleteBook(long id){
        bookDao.deleteById(id);
        return true;
     }

    public Book findAuthorsBook(String author){
        try {
          Author author1=service2.findByName(author);
        return bookDao.readById(service.findAuthorsBookId(author1.getId()));
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void insertBook(Book book) throws ServiceException {
        try {
            bookDao.create(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}
