package com.saulms.cavernwarrior;

import com.saulms.cavernwarrior.entities.Enemy;

import java.util.List;

public class World {

    private String stageId;
    private double width, height;
    private double spawnX, spawnY;
    private String tileset;
    private double tilesize;
    private List<List<Integer>> tilemap;
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

    public String getTileset() {
        return tileset;
    }

    public void setTileset(String tileset) {
        this.tileset = tileset;
    }

    public double getTilesize() {
        return tilesize;
    }

    public void setTilesize(double tilesize) {
        this.tilesize = tilesize;
    }

    public List<List<Integer>> getTilemap() {
        return tilemap;
    }

    public void setTilemap(List<List<Integer>> tilemap) {
        this.tilemap = tilemap;
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
