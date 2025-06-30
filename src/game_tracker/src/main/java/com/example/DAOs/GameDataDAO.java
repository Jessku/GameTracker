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
        try {
            establishConnection();
             
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO GameData (game_name, game_platform) VALUES (?, ?)");
            pStatement.setString(1, entity.getGameName());
            pStatement.setString(2, entity.getGamePlatform());
            int rowsAffected = pStatement.executeUpdate();
            if (rowsAffected > 0) return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database connection error");
        }

        return false;
    }

    @Override
    public GameData getById(int id) {
       try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM GameData WHERE game_id = ?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            rs.next();
            GameData returnGame = new GameData(rs.getInt("game_id"), rs.getString("game_name"), rs.getString("game_platform"));
            rs.close();
            return returnGame;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return null; // Return null if no game found or an error occurs
    }

    @Override
    public List<GameData> getByCondition(String condition) throws SQLException {
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM GameData WHERE game_platform = ?");
            pStatement.setString(1, condition);
            ResultSet rs = pStatement.executeQuery();
            List<GameData> returnGames = new ArrayList<>();
            while(rs.next()){
                //Create UserData object from result set
                GameData game = new GameData(rs.getInt("game_id"), rs.getString("game_name"), rs.getString("game_platform"));
                returnGames.add(game);
            }
            rs.close();
            return returnGames;
        }
        catch(SQLException e){
            e.printStackTrace();
            throw e;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new SQLException("Database connection error");
        }
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
