package com.example.DAOs;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.example.Objs.ListData;
import com.example.Objs.ListItems;
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
   
    public void register(){ //This method will handle the registration process.

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

    public int mainMenu(){
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
        Integer choice1;
        Integer choice2;
        Integer choice3;
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
                ListDAO.getListForUser(user_id).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list
                System.out.println("\n 1. Edit List Name");
                System.out.println(" 2. Add Game");
                System.out.println(" 3. Edit Game Progress");
                System.out.println(" 4. Exit");
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
                    ListDAO.getListForUser(user_id).forEach(e -> {System.out.println(e.toString());}); //Prints the actual game list1
                    
                }
                else if(choice2 == 3){
                    ListDAO.getListForUser(user_id).forEach(e -> {System.out.println(e.toString());});
                    System.out.println("\nPlease enter the item_id of the item you wish to update the status of: ");
                    choice3 = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if(itemsDAO.update(itemsDAO.getById(choice3))){
                        System.out.println("Item status updated successfully.");
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



    public static void main(String[] args) {  //This is the main method that starts the application.
        
        Main main = new Main(); // Create an instance of the Main class to access its methods
        int loginChoice = main.loginMenu(); //It will call the loginMenu method to display the login options.
        UserData user = null; //Saves the UserData of the logged in user as an instance
        boolean isExit = false;




        // Handle user choice
        switch (loginChoice) {
            case 1:
                System.out.println("Login selected.");
                user = main.login();
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

        //MAIN MENU
        ListItemsDAO listItemsDAO = new ListItemsDAO();

        List<ListItems> listItems = listItemsDAO.getAll();


         // Handle user choice
         while(!isExit){
            int mainChoice = main.mainMenu(); //It will call the mainMenu method to display the main menu options.
            switch (mainChoice) {
            case 1:
                System.out.println("View/Edit Lists selected.");
                main.viewEditList(user.getUserId());
                break;
            case 2:
                System.out.println("Create List selected.");
                main.register();
                break;
            case 3:
                System.out.println("Delete List selected.");
                break;
            case 4:
                System.out.println("Change Username selected.");
                break;
            case 5:
                System.out.println("Change Password selected.");
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
