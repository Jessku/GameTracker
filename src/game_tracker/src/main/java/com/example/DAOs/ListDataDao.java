package com.example.DAOs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListDataDao<E> implements DAOInterface<E> {

    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {
        // Implementation for establishing connection
        //if(connection == null)
    }

    @Override
    public void closeConnection() throws SQLException {
        // Implementation for closing connection
    }

    @Override
    public List getAll() {
        // Implementation for getAll
        return null;
    }

    @Override
    public void create(E entity) throws SQLException {
        // Implementation for create
    }

    @Override
    public E getById(int id) {
        // Implementation for getById
        return null;
    }

    @Override
    public List getByCondition(String condition) throws SQLException {
        // Implementation for getByCondition
        return null;
    }

    @Override
    public boolean update(E entity) {
        // Implementation for update
        return false;
    }

    @Override
    public boolean delete(int id) {
        // Implementation for delete
        return false;
    }

}
