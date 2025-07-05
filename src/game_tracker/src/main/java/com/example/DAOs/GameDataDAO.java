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
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM GameData ORDER BY game_id");
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
         // This can be used to update game name and game platform
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE GameData SET game_name = ?, game_platform = ? WHERE game_id = ?");
            pStatement.setString(1, entity.getGameName());
            pStatement.setString(2, entity.getGamePlatform());
            pStatement.setInt(3, entity.getGameId());
            int rowsAffected = pStatement.executeUpdate();
            if(rowsAffected > 0) return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return false; // Return false if update fails
    }

    public boolean update(GameData entity, String newValue) {
         // This can be used to update just the game platform, as this would be the most likely case
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE GameData SET game_platform = ? WHERE game_id = ?");
            pStatement.setString(1, newValue);
            pStatement.setInt(2, entity.getGameId());
            int rowsAffected = pStatement.executeUpdate();
            if(rowsAffected > 0) return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return false; // Return false if update fails
    }

    @Override
    public boolean delete(int id) {
       try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM GameData WHERE game_id = ?");
            pStatement.setInt(1, id);
            int rowsAffected = pStatement.executeUpdate();
            if(rowsAffected > 0) return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return false; // Return false if delete fails
    }

    public boolean delete(String name, String platform) {
       try{
            //This method can be used to delete a game that was listed on the wrong platform, as this is a more likely case
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM GameData WHERE game_name = ? AND game_platform = ?");
            pStatement.setString(1, name);
            pStatement.setString(2, platform);
            int rowsAffected = pStatement.executeUpdate();
            if(rowsAffected > 0) return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
        return false; // Return false if delete fails
    }

}
