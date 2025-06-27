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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List getByCondition(String condition) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByCondition'");
    }

    @Override
    public boolean update(Object entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // Implement methods from DAOInterface here
    // For example:
    // public void saveUserData(UserData userData) {
    //     // Logic to save user data
    // }

    // Additional methods specific to UserDataDAO can be added here

}
