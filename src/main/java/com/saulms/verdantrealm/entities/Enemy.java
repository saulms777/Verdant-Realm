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
    private EnemyType id;
    private int level;

    public void initialize() {
        EnemyData.Stats e = EnemyData.getStats(id);

        maxHealthPoints = 100 * level;
        healthPoints = maxHealthPoints;

        image = GameResource.loadEnemy(e.image());
        sprite = new ImageView(image);
        sprite.setFitWidth(e.width());
        sprite.setFitHeight(e.height());

        hpBarWidth = e.width() + 10;
        Rectangle hpBarBackground = new Rectangle(hpBarWidth, 10, Color.GRAY);
        hpBarBackground.setTranslateX(-5);
        hpBarBackground.setTranslateY(-30);
        hpBar = new Rectangle(hpBarWidth, 10, Color.LIMEGREEN);
        hpBar.setTranslateX(-5);
        hpBar.setTranslateY(-30);

        spriteGroup = new Group(sprite, hpBarBackground, hpBar);
        spriteGroup.setLayoutX(x);
        spriteGroup.setLayoutY(y);
    }

    @Override
    public Node getSprite() {
        return spriteGroup;
    }

    public EnemyType getId() {
        return id;
    }

    public void setId(EnemyType id) {
        this.id = id;
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
        hpBar.setWidth(hpBarWidth * healthPoints / maxHealthPoints);
    }

}
