package com.example.Objs;

public class UserData {

    //Declare Variables
    private Integer userId;
    private String userName;
    private String userPassword;

    //Constructor
    public UserData(Integer userId, String userName, String userPassword) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserData(String userName, String userPassword) {
        super();
        this.userId = null; // Assuming userId is auto-generated
        this.userName = userName;
        this.userPassword = userPassword;
    }

    //Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserData [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + "]";
    }


    



}
