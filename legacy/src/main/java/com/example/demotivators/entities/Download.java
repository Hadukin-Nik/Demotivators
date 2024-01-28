package com.example.demotivators.entities;

import java.util.Date;

public class Download {
    private int userId;
    private int collectionId;

    private Date creationDate;
    private Date updateDate;

    private int process;

    public Download(int userId, int collectionId, Date creationDate) {
        this.userId = userId;
        this.collectionId = collectionId;
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public int getProcess() {
        return process;
    }
}
