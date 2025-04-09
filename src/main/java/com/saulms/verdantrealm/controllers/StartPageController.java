package com.saulms.verdantrealm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartPageController extends Controller {

    @FXML private Label startLabel;
    @FXML private Label optionsLabel;
    @FXML private Label exitLabel;

    @FXML
    @Override
    protected void initialize() {
        setHoverAnimation(startLabel);
        setHoverAnimation(optionsLabel);
        setHoverAnimation(exitLabel);
    }

    @FXML
    private void start() {
        gameEngine.loadPage("view/save-select-view.fxml");
    }

    @FXML
    private void options() {
        gameEngine.loadPage("view/options-view.fxml");
    }

    @FXML
    private void exit() {
        gameEngine.exit();
    }

}
