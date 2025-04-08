package com.saulms.cavernwarrior.entities;

import com.saulms.cavernwarrior.data.GameResource;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity {

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        image = new Image(GameResource.loadImage("test_player.png"));
        sprite = new ImageView(image);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(64);
        sprite.setFitHeight(64);
    }

}
