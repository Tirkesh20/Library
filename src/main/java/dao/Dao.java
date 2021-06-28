package dao;

import exceptions.DaoException;

import java.sql.Connection;

public abstract class Dao<T> {

    public Connection connection=ConnectionBuilder.getConnection();

    public T create(T t) throws DaoException {
        return  null;
    };

    public void delete(){};

    public void deleteById(long id){}


    public void update(T t){};


    public T read(T t){
        return  null;
    };

    public T readById(long id) throws DaoException {
        return null;
    }
}
