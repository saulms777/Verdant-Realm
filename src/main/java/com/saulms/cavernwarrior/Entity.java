package com.saulms.cavernwarrior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Entity {

    protected Rectangle sprite;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public Entity(double width, double height) {
        this.width = width;
        this.height = height;
        sprite = new Rectangle(width, height);
        sprite.setFill(Color.BLACK);
    }

    public Rectangle getSprite() {
        return sprite;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setLayoutX(double x) {
        sprite.setLayoutX(x);
    }

    public double getWidth() {
        return width;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setLayoutY(double y) {
        sprite.setLayoutY(y);
    }

    public double getHeight() {
        return height;
    }

}
