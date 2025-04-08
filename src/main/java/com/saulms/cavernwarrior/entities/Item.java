package com.saulms.cavernwarrior.entities;

public class Item extends Entity {

    private ItemType type;

    public Item() {}

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

}
