package com.saulms.verdantrealm.entities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.saulms.verdantrealm.data.GameResource;

import java.util.HashMap;

public class EnemyData {

    private static HashMap<EnemyType, EnemyStats> enemies;
    public record Stats(String image, String name, int width, int height) {}

    public static void load() {
        enemies = GameResource.loadJson("enemies.json", new TypeReference<>() {});
    }

    public static Stats getStats(EnemyType id) {
        EnemyStats e = enemies.get(id);
        return new Stats(e.getImage(), e.getName(), e.getWidth(), e.getHeight());
    }

    public static class EnemyStats {

        private String image;
        private String name;
        private int width;
        private int height;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    }

}
