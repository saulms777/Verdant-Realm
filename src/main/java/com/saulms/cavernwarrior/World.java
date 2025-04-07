package com.saulms.cavernwarrior;

import com.saulms.cavernwarrior.entities.Enemy;

import java.util.List;

public class World {

    private String stageId;
    private double width;
    private double height;
    private double spawnX;
    private double spawnY;
    private String backgroundPath;
    private List<List<Integer>> collision;
    private List<Enemy> enemies;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(double spawnX) {
        this.spawnX = spawnX;
    }

    public double getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(double spawnY) {
        this.spawnY = spawnY;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public List<List<Integer>> getCollision() {
        return collision;
    }

    public void setCollision(List<List<Integer>> collision) {
        this.collision = collision;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

}
