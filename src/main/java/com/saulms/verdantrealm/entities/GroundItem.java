package com.saulms.verdantrealm.entities;

public class GroundItem extends Entity {

    private GroundItemType type;

    public GroundItem() {}

    public GroundItemType getType() {
        return type;
    }

    public void setType(GroundItemType type) {
        this.type = type;
    }

}
