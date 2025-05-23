package com.saulms.verdantrealm.entities;

import com.saulms.verdantrealm.data.GameResource;
import com.saulms.verdantrealm.weapons.Weapon;
import javafx.scene.image.ImageView;

public class Player extends Entity {

    private Weapon weapon;

    public Player(int x, int y, double healthPoints, Direction direction) {
        this.x = x;
        this.y = y;
        this.healthPoints = healthPoints;
        this.direction = direction;
        image = GameResource.loadPlayer("test_player.png");
        sprite = new ImageView(image);
        sprite.setLayoutX(x);
        sprite.setLayoutY(y);
        sprite.setFitWidth(64);
        sprite.setFitHeight(64);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        weapon.setPlayer(this);
    }

}
