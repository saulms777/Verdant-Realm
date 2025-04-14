package com.saulms.verdantrealm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saulms.verdantrealm.data.GameResource;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy extends Entity {

    @JsonIgnore private Group spriteGroup;
    @JsonIgnore private Rectangle hpBar;
    private EnemyType type;
    private int level;

    public Enemy() {}

    public void initialize() {
        image = GameResource.loadEnemy(type);
        sprite = new ImageView(image);
        sprite.setFitWidth(type.width);
        sprite.setFitHeight(type.height);

        Rectangle hpBarBackground = new Rectangle(type.width + 10, 10, Color.GRAY);
        hpBarBackground.setTranslateX(-5);
        hpBarBackground.setTranslateY(-40);
        hpBar = new Rectangle(type.width + 10, 10, Color.LIMEGREEN);
        hpBar.setTranslateX(-5);
        hpBar.setTranslateY(-40);
        spriteGroup = new Group(sprite, hpBarBackground, hpBar);

        spriteGroup.setLayoutX(x);
        spriteGroup.setLayoutY(y);
    }

    @Override
    public Node getSprite() {
        return spriteGroup;
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

    @Override
    public void setHealthPoints(double healthPoints) {
        super.setHealthPoints(healthPoints);
        if (spriteGroup == null) return;
        hpBar.setWidth((type.width + 10) * healthPoints / maxHealthPoints);
    }

}
