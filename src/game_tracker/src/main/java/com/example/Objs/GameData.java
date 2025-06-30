package com.example.Objs;

public class GameData {
    //Deckare Variables
    private Integer gameId;
    //private Integer user_id;
    private String gameName;
    private String gamePlatform;

    //Constructors
    public GameData(Integer id, String game_name, String game_platform) {
        super();
        this.gameId = id;
        //this.user_id = user_id;
        this.gameName = game_name;
        this.gamePlatform = game_platform;
    }

     public GameData(String game_name, String game_platform) {
        super();
        this.gameId = null; // Assuming id is auto-generated
        //this.user_id = null; // Assuming user_id is set later
        this.gameName = game_name;
        this.gamePlatform = game_platform;
    }

    //Getters and Setters
    public Integer getGameId() {
        return gameId;
    }

    public void setId(Integer id) {
        this.gameId = id;
    }

    // public Integer getUser_id() {
    //     return user_id;
    // }

    // public void setUser_id(Integer user_id) {
    //     this.user_id = user_id;
    // }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String game_name) {
        this.gameName = game_name;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public void setGamePlatform(String game_platform) {
        this.gamePlatform = game_platform;
    }

    @Override
    public String toString() {
        return "GameData [id=" + gameId + ", game_name=" + gameName + ", game_platform=" + gamePlatform + "]";
    }
    


}
