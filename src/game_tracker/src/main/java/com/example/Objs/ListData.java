package com.example.Objs;

public class ListData {

    //Declare Vartiables
    private Integer listId;
    private Integer userId;

    //Constructor
    public ListData(Integer id, Integer userId) {
        super();
        this.listId = id;
        this.userId = userId;
    }

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

    @Override
    public String toString() {
        return "ListData [id=" + listId + ", userId=" + userId + "]";
    }

}
