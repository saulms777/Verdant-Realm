package com.saulms.cavernwarrior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Entity {

    protected final Rectangle sprite;
    protected double x;
    protected double y;
    protected final double width;
    protected final double height;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
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

    public double getWidth() {
        return width;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getHeight() {
        return height;
    }

}
