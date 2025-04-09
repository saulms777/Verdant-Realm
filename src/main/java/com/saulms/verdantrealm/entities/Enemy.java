package com.saulms.verdantrealm.entities;

public class Enemy extends Entity {

    private EnemyType type;
    private int level;

    public Enemy() {}

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
