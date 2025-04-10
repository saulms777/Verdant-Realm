package com.saulms.verdantrealm.entities;

public class DroppedItem extends Entity {

    private DroppedItemType type;

    public DroppedItem() {}

    public DroppedItemType getType() {
        return type;
    }

    public void setType(DroppedItemType type) {
        this.type = type;
    }

}
