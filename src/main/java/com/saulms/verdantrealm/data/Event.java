package com.saulms.verdantrealm.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.geometry.Rectangle2D;

public class Event {

    private EventTriggerType trigger;

    @JsonSerialize(using = Rectangle2DSerializer.class)
    @JsonDeserialize(using = Rectangle2DDeserializer.class)
    private Rectangle2D area;

    public ActionType actionType;
    @JsonIgnore public transient Runnable action;

    public void loadAction() {
        switch (actionType) {
            case ActionType.SPAWN_WAVE -> action = () -> System.out.println("Spawning wave");
            case ActionType.OPEN_DOOR -> action = () -> System.out.println("Opening door");
            default -> throw new RuntimeException();
        }
    }

    public EventTriggerType getTrigger() {
        return trigger;
    }

    public void setTrigger(EventTriggerType trigger) {
        this.trigger = trigger;
    }

    public Rectangle2D getArea() {
        return area;
    }

    public void setArea(Rectangle2D area) {
        this.area = area;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

}
