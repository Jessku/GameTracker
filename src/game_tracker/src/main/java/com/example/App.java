package com.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.DAOs.DAOInterface;
import com.example.DAOs.GameDataDAO;
import com.example.DAOs.ListDataDAO;
import com.example.DAOs.UserDataDAO;
import com.example.Objs.GameData;
import com.example.Objs.ListData;
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
        UserData newUser3 = new UserData(3, "Axl Low", "0ut0f7h380x"); //New user to be updated
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
            System.out.println();
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
        GameDataDAO gameData = new GameDataDAO();
        List<GameData> gameList = gameDAO.getAll(); //Initial game list
        GameData newGame1 = new GameData("Guilty Gear Strive", "PS5"); //New game to be added
        GameData newGame2 = new GameData("Triangle Strategy", "Switch"); //New game to be added
        GameData newGame3 = new GameData("Fire Emblem: Echoes", "3DS"); //New game to be added
        GameData newGame4 = new GameData(3, "Gravity Rush", "PSVita"); //New game to be updated
        GameData newGame5 = new GameData(4, "Tekken 5", "Wii"); //New game to be added
        List<GameData> filteredGames = new ArrayList<>(); //List to hold filtered games
        String delName = "Tekken 5"; //Name of the game to be deleted
        String delPlatform = "Wii"; //Platform of the game to be deleted

        //GameDataDAO.getAll()
        gameList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //GameDataDAO.create()
        try {
            if(gameDAO.create(newGame1) && gameDAO.create(newGame2) && gameDAO.create(newGame3) && gameDAO.create(newGame5)) {
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
            System.out.println();
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

        //GameDataDAO.update()
        if(gameData.update(gameList.get(3), "Steam")) {
            System.out.println("Game platform updated successfully.");
        } else {
            System.out.println("Failed to update game platform.");
        }
        if(gameDAO.update(newGame4)) {
            System.out.println("Game name and platform updated successfully.");
        } else {
            System.out.println("Failed to update game name and platform.");
        }
        gameList = gameDAO.getAll(); //Refresh the game list after adding a new game
        gameList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //GameDataDAO.delete()
        if(gameDAO.delete(delid)) {
            System.out.println("Game deleted successfully.");
        } else {
            System.out.println("Failed to delete game. Game may not exist.");
        }

        if(gameData.delete(delName, delPlatform)) {
            System.out.println("Game deleted successfully.");
        } else {
            System.out.println("Failed to delete game. Game may not exist.");
        }
        gameList = gameDAO.getAll(); //Refresh the game list after adding a new game
        gameList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();






        System.out.println("\n**********LIST DATA DAO TEST**********");

        //Declare Variables
        DAOInterface listDAO = new ListDataDAO(); //DAO for ListData
        ListDataDAO listData = new ListDataDAO(); //For overloaded methods
        List<ListData> listList = listDAO.getAll(); //Initial list list
        List<ListData> filteredLists = new ArrayList<>(); //List to hold filtered lists
        ListData newList1 = new ListData(1, 2); //New list to be added
        ListData newList2 = new ListData(2, 3, "Hatsune Games"); //New list to be added
        ListData newList3 = new ListData(3, 2, "Gamer Games"); //New list to be added

        //ListDataDAO.getAll()
        listList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //ListDataDAO.create()
        try {
            listDAO.create(newList2);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            listData.create(newList1); //Using overloaded method
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        listList = listDAO.getAll(); //Refresh the list of lists after adding a new game
        listList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //ListDataDAO.getById()
        if(listDAO.getById(getid) == null) {
            System.out.println("List not found.");
            System.out.println();
        } else { 
            System.out.println(listDAO.getById(2).toString());
            System.out.println();
        }

        //ListDataDAO.getByCondition()
        try {
            filteredLists = listDAO.getByCondition("Hatsune Games");
            filteredLists.forEach(e -> {System.out.println(e.toString());});
            System.out.println();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        //ListDataDAO.update()
        if(listData.update(listList.get(0), "Axl's Games")) {
            System.out.println("List name updated successfully.");
        } else {
            System.out.println("Failed to update list name.");
        }
        if(listData.update(newList3)) {
            System.out.println("List name updated successfully.");
        } else {
            System.out.println("Failed to update list name.");
        }

        listList = listDAO.getAll(); //Refresh the list of lists after adding a new game
        listList.forEach(e-> {System.out.println(e.toString());});
        System.out.println();

        //ListDataDAO.delete()
        if(listDAO.delete(4)) {
            System.out.println("List deleted successfully.");
            listList = listDAO.getAll(); //Refresh the list of lists after adding a new game
            listList.forEach(e-> {System.out.println(e.toString());});
            System.out.println();
        } else {
            System.out.println("Failed to delete list. List may not exist.");
            System.out.println();
        }

        




         

    }
}

