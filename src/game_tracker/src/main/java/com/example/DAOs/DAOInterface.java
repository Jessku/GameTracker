package com.example.DAOs;

public interface DAOInterface {

    public void establishConnection() throws ClassNotFoundException, SQLException;
    public void closeConnection() throws SQLException;

    public List<E> getAll();
    public void create(E entity) throws SQLException;
    public E getById(int id);
    public List<E> getByCondition(String condition) throws SQLException;
    public boolean update(E entity);
    public boolean delete(int id);
    


}
