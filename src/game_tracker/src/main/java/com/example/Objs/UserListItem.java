package com.example.Objs;

public class UserListItem {
    //Declare Variables
    private String list_name;
    private Integer item_id;
    private String game_name;
    private String game_platform;
    private String item_status;

    //Constructor
    public UserListItem(String list_name, Integer item_id, String game_name, String game_platform, String item_status){
        this.list_name = list_name;
        this.item_id = item_id;
        this.game_name = game_name;
        this.game_platform = game_platform;
        this.item_status = item_status;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_platform() {
        return game_platform;
    }

    public void setGame_platform(String game_platform) {
        this.game_platform = game_platform;
    }

    public String getItem_status() {
        return item_status;
    }

    public void setItem_status(String item_status) {
        this.item_status = item_status;
    }

    @Override
    public String toString() {
        return "UserListItem [list_name=" + list_name + ", item_id=" + item_id + ", game_name=" + game_name + ", game_platform=" + game_platform + ", item_status=" + item_status + "]";
    }
    

}
