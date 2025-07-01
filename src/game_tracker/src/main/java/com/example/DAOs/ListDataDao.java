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
                ListData newList = new ListData(rs.getInt("list_id"), rs.getInt("user_id"));
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
        // Implementation for getById
        return null;
    }

    @Override
    public List getByCondition(String condition) throws SQLException {
        // Implementation for getByCondition
        return null;
    }

    @Override
    public boolean update(ListData entity) {
        // Implementation for update
        return false;
    }

    @Override
    public boolean delete(int id) {
        // Implementation for delete
        return false;
    }

}
