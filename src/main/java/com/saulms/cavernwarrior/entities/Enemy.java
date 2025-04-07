package com.saulms.cavernwarrior.entities;

public class Enemy extends Entity {

    private String type;
    private int level;

    public Enemy() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
