package com.saulms.verdantrealm.weapons.Melee;

import com.saulms.verdantrealm.weapons.Weapon;
import javafx.geometry.Rectangle2D;

public class Dagger extends Weapon {

    @Override
    public Rectangle2D getHitBox() {
        return switch (player.getDirection()) {
            case LEFT -> new Rectangle2D(player.getLeft() - range, player.getTop() - 16, range, 32);
            case RIGHT -> new Rectangle2D(player.getRight(), player.getTop() - 16, range, 32);
            case UP -> new Rectangle2D(player.getLeft() + 16, player.getTop() - range, 32, range);
            case DOWN -> new Rectangle2D(player.getLeft() + 16, player.getBottom(), 32, range);
        };
    }

}
