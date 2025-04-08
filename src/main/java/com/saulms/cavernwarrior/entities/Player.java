package com.saulms.cavernwarrior.entities;

import com.saulms.cavernwarrior.GameResource;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity {

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        image = new Image(GameResource.loadImage("test_player.png"));
        sprite = new ImageView(image);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(100);
        sprite.setFitHeight(100);
    }

}
