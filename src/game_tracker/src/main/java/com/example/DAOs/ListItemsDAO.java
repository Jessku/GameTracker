package com.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.ConnectionManager;
import com.example.CustomExceptions.DuplicateUsernameException;
import com.example.Objs.GameData;
import com.example.Objs.ListData;
import com.example.Objs.ListItems;
import com.example.Objs.UserListItem;

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
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM ListItems ORDER BY item_id");
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
            boolean isUpdateTime = false; //controls if we execute tStatement
            PreparedStatement tStatement = null;
            PreparedStatement pStatement = connection.prepareStatement("UPDATE ListItems SET item_status = ?::status_enum WHERE item_id = ?");
            //Depending of the status of the item, we will update the specific field
            if(entity.getStatus().equals("not_started")) {
                 pStatement.setString(1, "in_progress");
                 tStatement = connection.prepareStatement("UPDATE ListItems SET item_inprogress_at = CURRENT_TIMESTAMP WHERE item_id = ?"); //Updates the approptiate timestamp column
                 isUpdateTime = true;
            }
            else if(entity.getStatus().equals("in_progress")){
                pStatement.setString(1, "completed");
                tStatement = connection.prepareStatement("UPDATE ListItems SET item_completed_at = CURRENT_TIMESTAMP WHERE item_id = ?"); //Updates the approptiate timestamp column
                isUpdateTime = true;
            }
            else if(entity.getStatus().equals("completed")) pStatement.setString(1, "not_started");
            
            pStatement.setInt(2, (entity).getItemId());
            tStatement.setInt(1, (entity).getItemId());

            int rowsAffected = pStatement.executeUpdate();
            if(isUpdateTime) tStatement.executeUpdate(); //Only updates if necessary
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

    public boolean addGame(ListData list, int user_id) {
        try{
            establishConnection();
            //Declare Variables
            GameDataDAO gameDAO = new GameDataDAO();
            List<GameData> games = gameDAO.getAll();
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO ListItems (list_id, game_id) VALUES (?, ?)");
            Scanner scanner = new Scanner(System.in);
            int choice;

            games.forEach(e -> {System.out.println(e.toString());});
            System.out.println("\nPlease enter the game_id of the game you wish to add (0 to exit): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if(dupeCheckGame(list, user_id, gameDAO.getById(choice))){
                System.out.println("Game already in list.");
                return false;
            }
            if(choice != 0){
                pStatement.setInt(1, list.getListId());
                pStatement.setInt(2, choice);
                if (pStatement.executeUpdate() > 0){
                    System.out.println(gameDAO.getById(choice).getGameName() + " added successfully.");
                    return true;
                }
                else{
                    System.out.println("Failed to add game.");
                    return false;
                }
            }
            else{
                System.out.println("Exiting...");
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private boolean dupeCheckGame(ListData list, int user_id, GameData game){ //This method checks for a duplicate game in the list and triggers DuplicateGameException
        //Declare Variables
        ListDataDAO listDAO = new ListDataDAO();
        List<UserListItem> games = listDAO.getListForUser(user_id, list.getListName());
        boolean returnVal = false;

        try{
             if(games.stream().anyMatch(e -> e.getGame_name().equals(game.getGameName()) && e.getGame_platform().equals(game.getGamePlatform()))){ //Since GameListItem doesn't store game_id, we can check both the name and platform instead
            returnVal = true;
            throw new DuplicateUsernameException("Game already in list");
        }
        }
        catch(DuplicateUsernameException e){
            System.out.println(e.getMessage());
        }
        return returnVal;
    }

}
