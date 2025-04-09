package com.saulms.verdantrealm.controllers;

import com.saulms.verdantrealm.GameEngine;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class Controller {

    protected GameEngine gameEngine;

    @FXML
    protected abstract void initialize();

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    protected void setHoverAnimation(Label label) {
        label.setOnMouseEntered(_ -> label.setStyle("-fx-font-size: 17px"));
        label.setOnMouseExited(_ -> label.setStyle("-fx-font-size: 16px"));
    }

    protected void setHoverAnimation(Pane container) {
        for (Node node : container.getChildren()) {
            if (node instanceof Label) {
                setHoverAnimation((Label) node);
            }
        }
    }

}
