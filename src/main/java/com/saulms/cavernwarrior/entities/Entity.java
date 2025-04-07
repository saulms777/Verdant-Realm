package com.saulms.cavernwarrior.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    @JsonIgnore protected Image image;
    @JsonIgnore protected ImageView sprite;
    protected double x;
    protected double y;

    public Entity() {}

    public ImageView getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
