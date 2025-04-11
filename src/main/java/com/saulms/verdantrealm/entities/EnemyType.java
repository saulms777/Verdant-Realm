package com.saulms.verdantrealm.entities;

public enum EnemyType {

    SLIME( "test_slime.png", 48, 48),
    ARCHER("", 64, 64);

    public final String path;
    public final int width;
    public final int height;

    EnemyType(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
    }

}
