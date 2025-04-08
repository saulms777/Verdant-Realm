package com.saulms.cavernwarrior.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    @JsonIgnore protected Image image;
    @JsonIgnore protected ImageView sprite;
    protected int x, y;

    public Entity() {}

    public ImageView getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getLeft() {
        return x;
    }

    public int getRight() {
        return x + (int) sprite.getFitWidth();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTop() {
        return y;
    }

    public int getBottom() {
        return y + (int) sprite.getFitHeight();
    }

}
