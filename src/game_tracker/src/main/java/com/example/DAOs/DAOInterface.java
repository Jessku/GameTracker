package com.example.DAOs;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<E> {

    public void establishConnection() throws ClassNotFoundException, SQLException;
    public void closeConnection() throws SQLException;

    public List<E> getAll();
    public void create(E entity) throws SQLException;
    public E getById(int id);
    public List<E> getByCondition(String condition) throws SQLException;
    public boolean update(E entity);
    public boolean delete(int id);
    


}
