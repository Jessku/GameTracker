package com.example.Objs;

public class ListData {

    //Declare Vartiables
    private Integer id;
    private Integer userId;

    //Constructor
    public ListData(Integer id, Integer userId) {
        super();
        this.id = id;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ListData [id=" + id + ", userId=" + userId + "]";
    }

}
