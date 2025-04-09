package com.saulms.verdantrealm.entities;

import com.saulms.verdantrealm.data.GameResource;
import javafx.scene.image.ImageView;

public class Player extends Entity {

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        image = GameResource.loadImage("test_player.png");
        sprite = new ImageView(image);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(64);
        sprite.setFitHeight(64);
    }

}
