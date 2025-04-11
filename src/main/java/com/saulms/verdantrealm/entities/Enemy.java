package com.saulms.verdantrealm.entities;

import com.saulms.verdantrealm.data.GameResource;
import javafx.scene.image.ImageView;

public class Enemy extends Entity {

    private EnemyType type;
    private int level;

    public Enemy() {}

    public void initialize() {
        image = GameResource.loadEnemy(type);
        sprite = new ImageView(image);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(type.width);
        sprite.setFitHeight(type.height);
    }

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
