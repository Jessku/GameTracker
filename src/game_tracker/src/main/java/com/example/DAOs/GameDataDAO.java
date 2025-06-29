package com.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ConnectionManager;
import com.example.Objs.GameData;
import com.example.Objs.UserData;

public class GameDataDAO implements DAOInterface<GameData> {

    // Declare Variables
    private Connection connection = null;

    @Override
    public void establishConnection() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            connection = ConnectionManager.getConnection();
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public List<GameData> getAll() {
         try{
            establishConnection();
            
            //Declare Variables
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM GameData");
            List<GameData> returnGames = new ArrayList<>();
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                //Create UserData object from result set
                GameData newGame = new GameData(rs.getInt("game_id"), rs.getString("game_name"), rs.getString("game_platform"));
                returnGames.add(newGame);
            }
            rs.close();
            return returnGames;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
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
