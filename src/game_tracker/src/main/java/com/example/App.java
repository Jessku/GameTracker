package com.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.DAOs.DAOInterface;
import com.example.DAOs.GameDataDAO;
import com.example.DAOs.UserDataDAO;
import com.example.Objs.GameData;
import com.example.Objs.UserData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Declare Variables
        DAOInterface userDAO = new UserDataDAO(); //DAO for UserData
        UserDataDAO userData = new UserDataDAO();
        List<UserData> userList = userDAO.getAll(); //Initial user list
        UserData newUser1 = new UserData("Hatsune Mike", "W0rld15M1n3"); //New user to be added
        UserData newUser2 = new UserData("Axl Low", "OutOfTheBox"); //New user to be added
        UserData newUser3 = new UserData(3, "Axl Low", "0ut0f7h380x"); //New user to be added
        Integer getid = 3;
        Integer delid = 1;
        Integer noid = 100; //ID that does not exist in the database


        System.out.println( "Hello World!" );

        System.out.println("**********USER DATA DAO TEST**********");

        //UserDataDAO.getAll()
        userList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //UserDataDAO.create()
        try {
            if(userDAO.create(newUser1) && userDAO.create(newUser2)) {
                userList = userDAO.getAll(); //Refresh the user list after adding a new user
                userList.forEach(e-> {System.out.println(e.toString());});
                System.out.println();

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        //UserDataDAO.getById()
        if(userDAO.getById(noid) == null) {
            System.out.println("User not found.");
        } else {
            System.out.println(userDAO.getById(getid).toString());
            System.out.println();
        }

        //UserDataDAO.update()
        if(userData.update(userList.get(1), 'n', "Hatsune Miku")) {
            System.out.println("Username updated successfully.");
        } else {
            System.out.println("Failed to update username.");
        }
        if(userData.update(userList.get(0), 'p', "NewPassword123")) {
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Failed to update password.");
        }
        if(userData.update(newUser3)) {
            System.out.println("Username and password updated successfully.");
        } else {
            System.out.println("Failed to update username and password.");
        }
        
        userList = userDAO.getAll(); //Refresh the user list after adding a new user
        userList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //UserDataDAO.delete()
        if(userDAO.delete(delid)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user. User may not exist.");
        }
        userList = userDAO.getAll(); //Refresh the user list after adding a new user
        userList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //UserDataDAO.getByCondition()
        try {
            List<UserData> filteredUsers = userDAO.getByCondition("Hatsune Miku");
            filteredUsers.forEach(e -> {System.out.println(e.toString());});
            System.out.println();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.println("\n**********GAME DATA DAO TEST**********");
        //Declare Variables
        DAOInterface gameDAO = new GameDataDAO(); //DAO for GameData
        List<GameData> gameList = gameDAO.getAll(); //Initial game list
        GameData newGame1 = new GameData("Guilty Gear Strive", "PS5"); //New game to be added
        GameData newGame2 = new GameData("Triangle Strategy", "Steam"); //New game to be added
        GameData newGame3 = new GameData("Fire Emblem: Echoes", "3DS");
        List<GameData> filteredGames = new ArrayList<>(); //List to hold filtered games

        //GameDataDAO.getAll()
        gameList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //GameDataDAO.create()
        try {
            if(gameDAO.create(newGame1) && gameDAO.create(newGame2) && gameDAO.create(newGame3)) {
                gameList = gameDAO.getAll(); //Refresh the game list after adding a new game
                gameList.forEach(e-> {System.out.println(e.toString());});
                System.out.println();
            } else {
                System.out.println("Failed to add new games.");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        //GameDataDAO.getById()
        if(gameDAO.getById(noid) == null) {
            System.out.println("Game not found.");
        } else {
            System.out.println(gameDAO.getById(getid).toString());
            System.out.println();
        }

        //GameDataDAO.getByCondition()
        try {
            filteredGames = gameDAO.getByCondition("PS5");
            filteredGames.forEach(e -> {System.out.println(e.toString());});
            System.out.println();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }





         

    }
}

