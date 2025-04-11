package com.saulms.verdantrealm.world;

import com.saulms.verdantrealm.entities.Enemy;
import com.saulms.verdantrealm.entities.GroundItem;

import java.util.List;

public class World {

    private String stageId;
    private int spawnX, spawnY;
    private String music;
    private String tileset;
    private int tilesize;
    private List<List<Integer>> tilemap;
    private List<List<Integer>> collision;
    private List<Enemy> enemies;
    private List<GroundItem> groundItems;
    private List<Event> events;

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
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

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
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

    public List<GroundItem> getGroundItems() {
        return groundItems;
    }

    public void setGroundItems(List<GroundItem> groundItems) {
        this.groundItems = groundItems;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        for (Event event : events)
            event.loadAction();
    }

}
