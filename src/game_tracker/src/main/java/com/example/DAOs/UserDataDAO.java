package com.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ConnectionManager;
import com.example.Objs.UserData;

public class UserDataDAO implements DAOInterface {

    //Declare Variables
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
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM UserData");
            List<UserData> returnUsers = new ArrayList<>();
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                //Create UserData object from result set
                UserData user = new UserData(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"));
                returnUsers.add(user);
            }
            rs.close();
            return returnUsers;
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
    public boolean create(Object entity) throws SQLException {
        try {
            establishConnection();
            if (entity instanceof UserData) {
                UserData newUser = (UserData) entity;
                PreparedStatement pStatement = connection.prepareStatement("INSERT INTO UserData (user_name, user_password) VALUES (?, ?)");
                pStatement.setString(1, newUser.getUserName());
                pStatement.setString(2, newUser.getUserPassword());
                int rowsAffected = pStatement.executeUpdate();
                if (rowsAffected > 0) return true;
            } else {
                throw new IllegalArgumentException("Entity must be of type UserData");
            }
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
    public Object getById(int id) {
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM UserData WHERE user_id = ?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            rs.next();
            UserData returnUser = new UserData(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"));
            rs.close();
            return returnUser;
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
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM UserData WHERE user_name = ?");
            pStatement.setString(1, condition);
            ResultSet rs = pStatement.executeQuery();
            List<UserData> returnUsers = new ArrayList<>();
            while(rs.next()){
                //Create UserData object from result set
                UserData user = new UserData(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"));
                returnUsers.add(user);
            }
            rs.close();
            return returnUsers;
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
    public boolean update(Object entity) {
        // This can be used to update username and password
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE UserData SET user_name = ?, user_password = ? WHERE user_id = ?");
            pStatement.setString(1, ((UserData) entity).getUserName());
            pStatement.setString(2, ((UserData) entity).getUserPassword());
            pStatement.setInt(3, ((UserData) entity).getUserId());
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

    public boolean update(Object entity, char updateType, String newValue) {
        // This can be used to update username and password
        try{
            establishConnection();
            PreparedStatement pStatement = null;
            //We'd have to check which data the user wants to update
            if(updateType == 'n')  pStatement = connection.prepareStatement("UPDATE UserData SET user_name = ? WHERE user_id = ?");
            else if(updateType == 'p')  pStatement = connection.prepareStatement("UPDATE UserData SET user_password = ? WHERE user_id = ?");
            else throw new IllegalArgumentException("Invalid update type. Use 'n' for username or 'p' for password.");
            
            pStatement.setString(1, newValue);
            pStatement.setInt(2, ((UserData) entity).getUserId());

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
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM UserData WHERE user_id = ?");
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

    // Implement methods from DAOInterface here
    // For example:
    // public void saveUserData(UserData userData) {
    //     // Logic to save user data
    // }

    // Additional methods specific to UserDataDAO can be added here

}
