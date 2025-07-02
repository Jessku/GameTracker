package com.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ConnectionManager;
import com.example.Objs.GameData;
import com.example.Objs.ListData;
import com.example.Objs.ListItems;
import com.example.Objs.UserData;

public class ListItemsDAO implements DAOInterface<ListItems> {
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
    public List<ListItems> getAll() {
        try{
            establishConnection();
            
            //Declare Variables
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListItems");
            List<ListItems> returnItems = new ArrayList<>();
            ResultSet rs = pStatement.executeQuery();
            while(rs.next()){
                //Create UserData object from result set
                ListItems newItem = new ListItems(rs.getInt("item_id"), rs.getInt("list_id"), rs.getInt("game_id"), rs.getString("item_status"), rs.getDate("item_added_at"), rs.getDate("item_inprogress_at"), rs.getDate("item_completed_at"));
                returnItems.add(newItem);
            }
            rs.close();
            return returnItems;
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
    public boolean create(ListItems entity) throws SQLException {
        try {
            establishConnection();
             
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ListItems (list_id, game_id) VALUES (?, ?)");
            pStatement.setInt(1, entity.getListId());
            pStatement.setInt(2, entity.getGameId());
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

     public boolean create(ListData entity1, GameData entity2) throws SQLException {
        try { //Overloaded method to create a list item using ListData and GameData objects. This is useful for when you want to create a list item without needing to instantiate a ListItems object directly.
            establishConnection();
             
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ListItems (list_id, game_id) VALUES (?, ?)");
            pStatement.setInt(1, entity1.getListId());
            pStatement.setInt(2, entity2.getGameId());
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
    public ListItems getById(int id) {
        try{
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListItems WHERE item_id = ?");
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            rs.next();
            ListItems returnItem = new ListItems(rs.getInt("item_id"), rs.getInt("list_id"), rs.getInt("game_id"), rs.getString("item_status"), rs.getDate("item_added_at"), rs.getDate("item_inprogress_at"), rs.getDate("item_completed_at"));
            rs.close();
            return returnItem;
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
    public List<ListItems> getByCondition(String condition) throws SQLException {
         try{ //This could be used to get all items with a specific status, for example "in progress" or "completed". Usable for an admin side of things
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListItems WHERE item_status = ?::status_enum"); //item_status is an enum type in the database
            pStatement.setString(1, condition);
            ResultSet rs = pStatement.executeQuery();
            List<ListItems> returnItems = new ArrayList<>();
            while(rs.next()){
                //Create UserData object from result set
                ListItems item = new ListItems(rs.getInt("item_id"), rs.getInt("list_id"), rs.getInt("game_id"), rs.getString("item_status"), rs.getDate("item_added_at"), rs.getDate("item_inprogress_at"), rs.getDate("item_completed_at"));
                returnItems.add(item);
            }
            rs.close();
            return returnItems;
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
    public boolean update(ListItems entity) {
        try{ //This automatically updates the status of the item from what could be a single button press in the UI
            establishConnection();
            PreparedStatement pStatement = connection.prepareStatement("UPDATE ListItems SET item_status = ?::status_enum WHERE item_id = ?");
            //Depending of the status of the item, we will update the specific field
            if(entity.getStatus().equals("not_started")) {
                 pStatement.setString(1, "in_progress");
            }
            else if(entity.getStatus().equals("in_progress"))  pStatement.setString(1, "completed");
            else if(entity.getStatus().equals("completed")) pStatement.setString(1, "not_started");
            
            pStatement.setInt(2, (entity).getItemId());

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
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM ListItems WHERE item_id = ?");
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
