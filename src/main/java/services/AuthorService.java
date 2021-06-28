package services;

import exceptions.DaoException;
import exceptions.ServiceException;
import dao.implementation.AuthorDao;
import entities.Author;

import java.util.List;

public class AuthorService {
    private final AuthorDao authorDao=new AuthorDao();

    public Author findAuthorById(long id) throws ServiceException {
        try {
          return   authorDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    public void insertAuthor(Author author) throws ServiceException {
        try {
            authorDao.create(author);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List listOfAuthors() throws ServiceException {
        try {
           return authorDao.select();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean deleteAuthor(long id){
        authorDao.deleteById(id);
        return true;
    }

    public Author findByName(String name)throws ServiceException{
        try {
           return authorDao.findByName(name);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

}

