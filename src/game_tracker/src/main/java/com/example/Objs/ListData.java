package com.example.Objs;

public class ListData {

    //Declare Vartiables
    private Integer listId;
    private Integer userId;
    private String listName;

    //Constructors
    public ListData(Integer id, Integer userId, String listName) {
        super();
        this.listId = id;
        this.userId = userId;
        this.listName = listName;
    }

     public ListData(Integer id, Integer userId) {
        super();
        this.listId = id;
        this.userId = userId;
        //this.listName = null;
    }

    public ListData(Integer userId, String listName) {
        super();
        this.userId = userId;
        this.listName = listName;
    }

    //Getters and Setters

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer id) {
        this.listId = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


    @Override
    public String toString() {
        return "ListData [listId=" + listId + ", userId=" + userId  + ", listName=" + listName + "]";
    }

}
