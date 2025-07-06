package com.example.DAOs;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.example.Objs.ListData;
import com.example.Objs.UserData;

public class Main {

    // This is the main class for the Game Tracker application.
    // It serves as the entry point for the application.

    //Define methods
    public int loginMenu(){// This method will display the login menu options to the user. It will return an integer representing the user's choice.

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

    public UserData login(){ //This method will handle the login process.
        
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
                    UserData returnUser = new UserData(userData.getUserId(), userData.getUserName(), userData.getUserPassword());
                    return returnUser;
                 
                }
            }
        if (!loginSuccess) System.out.println("Login failed, please try again.");
    }
        return null;
}
   
    public UserData register(){ //This method will handle the registration process.

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
                return newUser;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Registration failed. Please try again.");
                return null;
            }
            


    }

    public int mainMenu(){ //This method will display the main menu. It will return an integer representing the user's choice
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        //Display menu options
        System.out.println("\nMain Menu:");

        System.out.println("1. View/Edit Lists");
        System.out.println("2. Create List");
        System.out.println("3. Delete List");
        System.out.println("4. Change Username");
        System.out.println("5. Change Password");
        System.out.println("0. Exit");

        //Get user input
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        //scanner.close();
        return choice; 
    }

    public void viewEditList(int user_id){ //This method will handle the viewing and editing of lists. 

        //Declare Variables
        ListDataDAO ListDAO = new ListDataDAO();
        ListItemsDAO itemsDAO = new ListItemsDAO();
        ListData listData = null;
        Scanner scanner = new Scanner(System.in);
        Integer choice1; //List num
        Integer choice2; // Menu num
        Integer choice3; //Update num
        Integer choice4; //Delete num
        String newName;

        try {
            List<ListData> lists = ListDAO.getByCondition(user_id);
            lists.forEach(e -> {System.out.println(e.toString());}); //Prints the user's lists

            System.out.println("\nPlease enter the list_id of the list you wish to edit (0 to exit): ");
            choice1 = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if(choice1 != 0){
                listData = ListDAO.getById(choice1); //copies instance of list
                System.out.println(listData.toString());
                ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                System.out.println("\n 1. Edit List Name");
                System.out.println(" 2. Add Game");
                System.out.println(" 3. Remove Game");
                System.out.println(" 4. Edit Game Progress");
                System.out.println(" 0. Exit");
                choice2 = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(choice2 == 1){
                     System.out.println("Please enter the new name: ");
                    newName = scanner.nextLine();
                    if(ListDAO.update(listData, newName)){
                        System.out.println("List name updated successfully.");
                    }
                    else {
                        System.out.println("Failed to update list name.");
                }
                }
                else if(choice2 == 2){
                    itemsDAO.addGame(listData);
                    ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                    
                }
                else if(choice2 == 3){
                    ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                    System.out.println("Please enter the item_id of the item you wish to remove: ");
                    choice4 = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if(itemsDAO.delete(choice4)){
                        System.out.println("Item removed successfully.");
                        ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                    }
                    else {
                        System.out.println("Failed to remove item.");
                    }
                }
                else if(choice2 == 4){
                      ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                    System.out.println("\nPlease enter the item_id of the item you wish to update the status of: ");
                    choice3 = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if(itemsDAO.update(itemsDAO.getById(choice3))){
                        System.out.println("Item status updated successfully.");
                        ListDAO.getListForUser(user_id, listData.getListName()).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                    }
                    else {
                        System.out.println("Failed to update item status.");
                    }

                }

            }
            else{
                System.out.println("Exiting...");
            }

            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void createList(int user_id){ //This method will handle the creation of lists.

        //Declare Variables
        Scanner scanner = new Scanner(System.in);
        ListDataDAO ListDAO = new ListDataDAO();
        String listName;

        System.out.println("Please enter the name of the list you'd like to create:");
        listName = scanner.nextLine();
        ListData newList = new ListData(user_id, listName);
        try {
            if(ListDAO.create(newList)){
                System.out.println("List created successfully.");
                ListDAO.getByCondition(user_id).forEach(e -> {System.out.println(e.toString());}); //Prints the user's lists
            }
            else System.out.println("Failed to create list.");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void deleteList(int user_id){ //This method will handle the deletion of lists
        //Declare Variables
        ListDataDAO ListDAO = new ListDataDAO();
        Integer delId;
        Scanner scanner = new Scanner(System.in);

        try {
            ListDAO.getByCondition(user_id).forEach(e -> {System.out.println(e.toString());});
            System.out.println("Please enter the list_id of the list you wish to delete: ");
            delId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if(ListDAO.delete(delId)){
                System.out.println("List deleted successfully.");
                ListDAO.getByCondition(user_id).forEach(e -> {System.out.println(e.toString());}); //Prints the user's lists
            }
            else System.out.println("Failed to delete list.");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

    public void changeUsername(UserData user){ //This method will handle updating username
        //Declare Variables
        Scanner scanner = new Scanner(System.in);
        UserDataDAO userDAO = new UserDataDAO();
        boolean confirm = false;
        String newName = null;
        Character choice;

       while(!confirm){
        System.out.println("Please enter the new username: ");
        newName = scanner.nextLine();
        System.out.println(newName + "is this right(y/n)?");
        choice = scanner.nextLine().charAt(0);
        if(choice == 'y') confirm = true;

       }

       if(userDAO.update(user, 'n', newName)){
        System.out.println("Username updated successfully.");
        user.setUserName(newName);
       }
       else System.out.println("Failed to update username.");
    }

    public void changePassword(UserData user){ //This method will handle updating password
         //Declare Variables
        Scanner scanner = new Scanner(System.in);
        UserDataDAO userDAO = new UserDataDAO();
        boolean confirm = false;
        String newPass = null;
        String oldPass = null;
        String confirmPass = null;

       while(!confirm){
        System.out.println("Please enter your current password: ");
        oldPass = scanner.nextLine();
        if(user.getUserPassword().equals(oldPass)){
            System.out.println("Please enter your new password: ");
            newPass = scanner.nextLine();
            System.out.println("Please confirm your new password:");
            confirmPass = scanner.nextLine();
            if(newPass.equals(confirmPass)) confirm = true;
            else System.out.println("Passwords do not match. Please try again.");
        }

       }

       if(userDAO.update(user, 'p', newPass)) {
        System.out.println("Password updated successfully.");
        user.setUserPassword(newPass);
    }
       else System.out.println("Failed to update password.");
    }



    public static void main(String[] args) {  //This is the main method that starts the application.
        
        Main main = new Main(); // Create an instance of the Main class to access its methods
        int loginChoice = main.loginMenu(); //It will call the loginMenu method to display the login options.
        UserData user = null; //Saves the UserData of the logged-in user as an instance
        boolean isExit = false; //Loops the program




        // Handle user choice
        switch (loginChoice) {
            case 1:
                System.out.println("Login selected.");
                user = main.login();
                break;
            case 2:
                System.out.println("Register selected.");
                user = main.register();
                break;
            case 3:
                System.out.println("Exiting application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        //MAIN MENU
         while(!isExit){
            int mainChoice = main.mainMenu(); //It will call the mainMenu method to display the main menu options.
            switch (mainChoice) { //Handles user choice
            case 1:
                System.out.println("View/Edit Lists selected.");
                main.viewEditList(user.getUserId());
                break;
            case 2:
                System.out.println("Create List selected.");
                main.createList(user.getUserId());
                break;
            case 3:
                System.out.println("Delete List selected.");
                main.deleteList(user.getUserId());
                break;
            case 4:
                System.out.println("Change Username selected.");
                main.changeUsername(user);
                break;
            case 5:
                System.out.println("Change Password selected.");
                main.changePassword(user);
                break;
            case 0:
                System.out.println("Exiting application.");
                isExit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
         }
        

        
        
    }

}
