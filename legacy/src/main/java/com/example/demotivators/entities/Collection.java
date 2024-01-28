package com.example.demotivators.entities;

public class Collection {
    private int id;
    private int creatorId;

    private String name;
    private String description;
    private String image;

    private boolean isPrivate;

    public Collection(int creatorId, String name, String description, boolean isPrivate) {
        this.creatorId = creatorId;
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
    }

    public Collection(int id, int creatorId, String name, String description, String image, boolean isPrivate) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.isPrivate = isPrivate;
    }

    public int getId() {
        return id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
