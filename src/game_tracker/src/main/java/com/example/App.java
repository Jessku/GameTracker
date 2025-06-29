package com.example;

import java.sql.SQLException;
import java.util.List;

import com.example.DAOs.DAOInterface;
import com.example.DAOs.UserDataDAO;
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


        System.out.println( "Hello World!" );

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
        System.out.println(userDAO.getById(getid).toString());
        System.out.println();

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


         

    }
}

