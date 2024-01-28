package com.example.demotivators.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Mem {
    private int memId;
    private int userId;

    private String picture;
    private String description;
    private String tags;


    private Date creationDate;
    private Date updateDate;

    private boolean commentsAllowed;

    public Mem(int memId, int userId, String picture, String decription, String tags, Date creationDate, Date updateDate, boolean commentsAllowed) {
        this.memId = memId;
        this.userId = userId;
        this.tags = tagsCleanUp(tags);
        this.picture = picture;
        this.description = decription;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.commentsAllowed = commentsAllowed;
    }

    public Mem(int userId, String picture, String decription, String tags, Date creationDate, Date updateDate, boolean commentsAllowed) {
        memId = -1;
        this.userId = userId;
        this.tags = tagsCleanUp(tags);
        this.picture = picture;
        this.description = decription;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.commentsAllowed = commentsAllowed;
    }

    private String tagsCleanUp(String tags) {
        String[] strings = tags.toLowerCase().split(" ");

        Arrays.sort(strings);

        StringBuilder ans = new StringBuilder();
        boolean n = false;
        for(var j : strings) {
            if(n) ans.append(" ");

            ans.append(j);
            n = true;
        }

        return ans.toString();
    }

    public boolean isCommentsAllowed() {
        return commentsAllowed;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        if(description == null) description = "";
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getPath() {return "images/" + picture;}


    public int getUserId() {
        return userId;
    }

    public int getMemId() {
        return memId;
    }

    public String getTags() {
        if(tags == null) tags = "";
        return tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String tags) {
        this.tags = tagsCleanUp(tags);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setCommentsAllowed(boolean commentsAllowed) {
        this.commentsAllowed = commentsAllowed;
    }
}
