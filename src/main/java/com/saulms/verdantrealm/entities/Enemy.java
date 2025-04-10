package com.saulms.verdantrealm.entities;

import com.saulms.verdantrealm.data.GameResource;
import javafx.scene.image.ImageView;

public class Enemy extends Entity {

    private EnemyType type;
    private int level;

    public Enemy() {}

    public void initialize() {
        sprite = new ImageView(image = switch (type) {
            case SLIME -> GameResource.loadImage("test_slime.png");
            case ARCHER -> null;
        });
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(64);
        sprite.setFitHeight(64);
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
