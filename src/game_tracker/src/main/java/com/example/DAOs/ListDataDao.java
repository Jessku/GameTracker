package com.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ConnectionManager;
import com.example.Objs.ListData;
import com.example.Objs.UserData;

public class ListDataDAO implements DAOInterface<ListData> {

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
    public List getAll() {
        try{
            establishConnection();
            
            //Declare Variables
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListData");
            List<ListData> returnLists = new ArrayList<>();
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                //Create UserData object from result set
                ListData newList = new ListData(rs.getInt("list_id"), rs.getInt("user_id"), rs.getString("list_name"));
                returnLists.add(newList);
            }
            rs.close();
            return returnLists;
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
    public boolean create(ListData entity) throws SQLException {
        try {
            establishConnection();
             
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ListData (user_id, list_name) VALUES (?, ?)");
            pStatement.setInt(1, entity.getUserId());
            pStatement.setString(2, entity.getListName());
        
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

    public boolean create(UserData entity) throws SQLException { //Function is called from UserDataDAO
        try {
            establishConnection();
             
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ListData (user_id) VALUES (?)");
            pStatement.setInt(1, entity.getUserId());
        
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
    public ListData getById(int id) {
         try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListData WHERE list_id = ?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            rs.next();
            ListData returnList = new ListData(rs.getInt("list_id"), rs.getInt("user_id"), rs.getString("list_name"));
            rs.close();
            return returnList;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return null; // Return null if no user found or an error occurs
    }

    @Override
    public List getByCondition(String condition) throws SQLException {
       try{ //Most common condition is to get a list for a user, so condition is the name of the list
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListData WHERE list_name = ?");
            pStatement.setString(1, condition);
            ResultSet rs = pStatement.executeQuery();
            List<ListData> returnLists = new ArrayList<>();
            while(rs.next()){
                //Create UserData object from result set
                ListData list = new ListData(rs.getInt("list_id"), rs.getInt("user_id"), rs.getString("list_name"));
                returnLists.add(list);
            }
            rs.close();
            return returnLists;
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
    public boolean update(ListData entity) {
         try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE ListData SET user_id = ?, list_name = ? WHERE list_id = ?");
            pStatement.setInt(1, (entity).getUserId());
            pStatement.setString(2, (entity).getListName());
            pStatement.setInt(3, (entity).getListId());
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

     public boolean update(ListData entity, String newListName) {
         try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE ListData SET list_name = ? WHERE list_id = ?");
            pStatement.setString(1, newListName);
            pStatement.setInt(2, (entity).getListId());
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
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM ListData WHERE list_id = ?");
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

}
