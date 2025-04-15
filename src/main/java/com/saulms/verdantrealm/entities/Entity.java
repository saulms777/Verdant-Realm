package com.saulms.verdantrealm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    @JsonIgnore protected Image image;
    @JsonIgnore protected ImageView sprite;
    @JsonIgnore protected double maxHealthPoints = Double.MAX_VALUE;
    protected int x, y;
    protected double healthPoints;
    protected Direction direction;

    public Entity() {}

    public Node getSprite() {
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

    public double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(double healthPoints) {
        if (healthPoints >= 0) this.healthPoints = healthPoints;
        if (maxHealthPoints == Double.MAX_VALUE) maxHealthPoints = healthPoints;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
