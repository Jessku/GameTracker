package com.example.DAOs;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.example.Objs.UserData;

public class Main {

    // This is the main class for the Game Tracker application.
    // It serves as the entry point for the application.

    //Define methods
    public int loginMenu(){
        // This method will display the login menu options to the user.
        // It will return an integer representing the user's choice.

        //Declare Variables
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        //Display menu options
        System.out.println("\nLogin Menu:");

        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        //Get user input
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        //scanner.close();
        return choice; 
    }

    public void login(){
        // This method will handle the login process.
        //Declare Variables
        Scanner scanner; //Used to read user input
        UserDataDAO user = new UserDataDAO(); // DAO for UserData
        List<UserData> usersList = user.getAll(); // Get all users from the database
        boolean loginSuccess = false; // Flag to check if login is successful

        while(!loginSuccess){ //This loop will continue until the user successfully logs in.
            //Reset scanner
            scanner = new Scanner(System.in);

            //Prompt user for username and password
            System.out.println("Username: ");
            String username = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();

            //Ckeck if username and password are valid
            for (UserData userData : usersList) {
                if (userData.getUserName().equals(username) && userData.getUserPassword().equals(password)) {
                    loginSuccess = true; // Set flag to true if credentials match
                    System.out.println("Login successful! Welcome, " + userData.getUserName() + "!");
                    break; // Exit loop if login is successful
                }
            }
        if (!loginSuccess) System.out.println("Login failed, please try again.");
    }
}
   
    public void register(){
        // This method will handle the registration process.

        //Declare Variables
        Scanner scanner = new Scanner(System.in); //Used to read user input
        UserDataDAO user = new UserDataDAO(); // DAO for UserData
        boolean confirm = false;
        String username = null;
        String password = null;


            while(!confirm){
                //Prompt user for username and password
                System.out.println("Username: ");
                username = scanner.nextLine();
                System.out.println("Password: ");
                password = scanner.nextLine();
                System.out.println("Username entered: " + username);
                System.out.println("Password entered: " + password);
                System.out.println("Is this right?(y/n)");
                char choice = scanner.nextLine().charAt(0);
                if(choice == 'y') confirm = true;
            }
            UserData newUser = new UserData(username, password);
            try {
                user.create(newUser);
                System.out.println("Registration successful! Welcome, " + username + "!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Registration failed. Please try again.");
            }
            


    }
    public static void main(String[] args) {
        // This is the main method that starts the application.
        // It will call the loginMenu method to display the login options.

        Main main = new Main(); // Create an instance of the Main class to access its methods
        int userChoice = main.loginMenu();

        // Handle user choice
        switch (userChoice) {
            case 1:
                System.out.println("Login selected.");
                main.login();
                break;
            case 2:
                System.out.println("Register selected.");
                main.register();
                break;
            case 3:
                System.out.println("Exiting application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
    }

}
