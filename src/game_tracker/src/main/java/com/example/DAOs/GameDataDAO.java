package com.example.DAOs;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.Objs.GameData;

public class GameDataDAO implements DAOInterface<GameData> {

    // Declare Variables
    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {
        // Implementation for establishing connection
    }

    @Override
    public void closeConnection() throws SQLException {
        // Implementation for closing connection
    }

    @Override
    public List<GameData> getAll() {
        // Implementation for getAll
        return null;
    }

    @Override
    public boolean create(GameData entity) throws SQLException {
        // Implementation for create
        return false;
    }

    @Override
    public GameData getById(int id) {
        // Implementation for getById
        return null;
    }

    @Override
    public List<GameData> getByCondition(String condition) throws SQLException {
        // Implementation for getByCondition
        return null;
    }

    @Override
    public boolean update(GameData entity) {
        // Implementation for update
        return false;
    }

    @Override
    public boolean delete(int id) {
        // Implementation for delete
        return false;
    }

}
