package services;

import exceptions.DaoException;
import exceptions.ServiceException;
import dao.implementation.AuthorBookDao;
import entities.Author;
import entities.Book;

public class AuthorBookConnectionService {
    private final AuthorBookDao authorBookDao=new AuthorBookDao();


    public void authorsBook(Author author , Book book) throws ServiceException {
        try {
            authorBookDao.insert(author,book);
        }catch (DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }

    public long findAuthorsBookId(long id)throws ServiceException{
        try {
            return authorBookDao.findAuthorsBookId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public long findBooksAuthorId(long id) throws ServiceException{
        try {
            return authorBookDao.findAuthorId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
