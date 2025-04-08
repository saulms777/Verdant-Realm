package com.saulms.cavernwarrior;

import com.saulms.cavernwarrior.entities.Enemy;

import java.util.List;

public class World {

    private String stageId;
    private int width, height;
    private int spawnX, spawnY;
    private String tileset;
    private int tilesize;
    private List<List<Integer>> tilemap;
    private List<List<Integer>> collision;
    private List<Enemy> enemies;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public String getTileset() {
        return tileset;
    }

    public void setTileset(String tileset) {
        this.tileset = tileset;
    }

    public int getTilesize() {
        return tilesize;
    }

    public void setTilesize(int tilesize) {
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
