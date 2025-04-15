package com.saulms.verdantrealm.weapons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saulms.verdantrealm.entities.Player;
import javafx.geometry.Rectangle2D;

public abstract class Weapon {

    @JsonIgnore protected Player player;
    protected String name;
    protected int tier;
    protected double range;
    protected double damage;
    protected double speed;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract Rectangle2D getHitBox();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
