package com.example.Objs;

public class GameData {
    //Deckare Variables
    private Integer id;
    private Integer user_id;
    private String game_name;
    private String game_platform;

    //Constructor
    public GameData(Integer id, Integer user_id, String game_name, String game_platform) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.game_name = game_name;
        this.game_platform = game_platform;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "GameData [id=" + id + ", user_id=" + user_id + ", game_name=" + game_name + ", game_platform=" + game_platform + "]";
    }
    


}
