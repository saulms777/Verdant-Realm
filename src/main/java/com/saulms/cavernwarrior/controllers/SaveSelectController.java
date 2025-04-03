package com.saulms.cavernwarrior.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class SaveSelectController extends Controller {

    private List<Label> saveFiles;

    @FXML private VBox savesVBox;
    @FXML private Label fileOptionsLabel;
    @FXML private Label backLabel;

    @FXML
    @Override
    protected void initialize() {
        saveFiles = savesVBox.getChildren().stream()
                .filter(n -> n instanceof Label)
                .map(n -> (Label) n)
                .collect(Collectors.toList());

        setHoverAnimation(savesVBox);
        setHoverAnimation(fileOptionsLabel);
        setHoverAnimation(backLabel);
    }

    @FXML
    private void selectFile(MouseEvent event) {
        Label saveFile = (Label) event.getSource();
        if (saveFile.getText().equals("Empty Save File")) {
            fileOptionsLabel.setText("Start New Game");
        } else {
            fileOptionsLabel.setText("Play Game");
        }
    }

    @FXML
    private void startGame() {
        gameEngine.loadGame();
    }

    @FXML
    private void back() {
        gameEngine.loadPage("view/start-page-view.fxml");
    }

}
