package com.example;

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
        DAOInterface userDAO = new UserDataDAO();
        List<UserData> userList = userDAO.getAll();


        System.out.println( "Hello World!" );
        userList.forEach(e-> {System.out.println(e.toString());});


    }
}
