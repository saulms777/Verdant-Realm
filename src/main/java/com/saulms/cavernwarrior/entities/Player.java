package com.saulms.cavernwarrior.entities;

import com.saulms.cavernwarrior.GameEngine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity {

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        image = new Image(GameEngine.getResource("/com/saulms/cavernwarrior/images/player.png"));
        sprite = new ImageView(image);
        sprite.setFitWidth(100);
        sprite.setFitHeight(100);
    }

}
