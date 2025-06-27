package com.example.Objs;

import java.sql.Date;

public class ListItems {

    //Declare Variables
    private Integer id;
    private Integer listId;
    private Integer gameId;
    private String status;
    private Date dateAdded;
    private Date dateInProgress;
    private Date dateCompleted;

    //Constructor
    public ListItems(Integer id, Integer listId, Integer gameId, String status, Date dateAdded, Date dateInProgress, Date dateCompleted) {
        super();
        this.id = id;
        this.listId = listId;
        this.gameId = gameId;
        this.status = status;
        this.dateAdded = dateAdded;
        this.dateInProgress = dateInProgress;
        this.dateCompleted = dateCompleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateInProgress() {
        return dateInProgress;
    }

    public void setDateInProgress(Date dateInProgress) {
        this.dateInProgress = dateInProgress;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Override
    public String toString() {
        return "ListItems [id=" + id + ", listId=" + listId + ", gameId=" + gameId + ", status=" + status + 
               ", dateAdded=" + dateAdded + ", dateInProgress=" + dateInProgress + ", dateCompleted=" + dateCompleted + "]";
    }
    
}
