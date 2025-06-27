package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    //Declare Variables
    private static final String URL = "jdbc:postgresql://localhost:5432/GameTracker";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "HatsuneMiku";

    private static Connection connection = null;


    public static void makeConnection() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null){
            makeConnection();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            System.out.println("Connection established successfully!");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            conn.close();
            System.out.println("Connection closed successfully!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
}
