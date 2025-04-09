package com.saulms.verdantrealm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class OptionsController extends Controller {

    @FXML private Label backLabel;

    @FXML
    @Override
    protected void initialize() {
        setHoverAnimation(backLabel);
    }

    @FXML
    private void back() {
        gameEngine.loadPage("view/start-page-view.fxml");
    }

}
