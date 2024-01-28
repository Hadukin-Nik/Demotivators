package com.example.demotivators.entities;

import java.util.Date;

public class Request {
    private int toUserId;
    private int fromUserId;
    private int requestId;

    private String text;

    private Date creationDate;

    private Date updateDate;

    private boolean watched;
    private boolean approved;

    public Request(int toUserId, int fromUserId, String text, Date creationDate) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.text = text;
        this.creationDate = creationDate;
    }

    public Request(int toUserId, int fromUserId, int requestId, String text, Date creationDate, Date updateDate, boolean watched, boolean approved) {
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.requestId = requestId;
        this.text = text;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.watched = watched;
        this.approved = approved;
    }

    public int getToUserId() {
        return toUserId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getText() {
        return text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public boolean isWatched() {
        return watched;
    }

    public boolean isApproved() {
        return approved;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
