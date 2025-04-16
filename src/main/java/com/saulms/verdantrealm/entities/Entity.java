package com.saulms.verdantrealm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    // json ignored fields
    @JsonIgnore protected Image image;
    @JsonIgnore protected ImageView sprite;
    @JsonIgnore protected double maxHealthPoints;
    @JsonIgnore protected int hpBarWidth;
    @JsonIgnore protected double healthPoints;

    public Node getSprite() {
        return sprite;
    }

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    // json fields
    protected int x, y;
    protected Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
