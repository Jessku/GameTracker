package com.example.Objs;

import java.sql.Date;

public class ListItems {

    //Declare Variables
    private Integer itemId;
    private Integer listId;
    private Integer gameId;
    private String status;
    private Date dateAdded;
    private Date dateInProgress;
    private Date dateCompleted;

    //Constructors
    public ListItems(Integer id, Integer listId, Integer gameId, String status, Date dateAdded, Date dateInProgress, Date dateCompleted) {
        super();
        this.itemId = id;
        this.listId = listId;
        this.gameId = gameId;
        this.status = status;
        this.dateAdded = dateAdded;
        this.dateInProgress = dateInProgress;
        this.dateCompleted = dateCompleted;
    }

    public ListItems(Integer listId, Integer gameId) {
        super();
        this.itemId = null; // Item ID will be auto-generated
        this.listId = listId;
        this.gameId = gameId;
        this.status = null; // Default status
        this.dateAdded = null;
        this.dateInProgress = null;
        this.dateCompleted = null;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer id) {
        this.itemId = id;
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
        return "ListItems [id=" + itemId + ", listId=" + listId + ", gameId=" + gameId + ", status=" + status + 
               ", dateAdded=" + dateAdded + ", dateInProgress=" + dateInProgress + ", dateCompleted=" + dateCompleted + "]";
    }
    
}
