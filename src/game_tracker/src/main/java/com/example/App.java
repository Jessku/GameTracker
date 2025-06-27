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
        List<UserData> userList = userDAO.getAll(); //Initial user list
        UserData newUser1 = new UserData("Hatsune Miku", "W0rld15M1n3"); //New user to be added
        UserData newUser2 = new UserData("Axl Low", "OutOfTheBox"); //New user to be added


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

         

    }
}
