package com.saulms.verdantrealm;

import com.saulms.verdantrealm.controllers.Controller;
import com.saulms.verdantrealm.controllers.GameController;
import com.saulms.verdantrealm.data.GameResource;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GameEngine extends Application {

    private Scene appRoot;
    private Controller viewController;
    private GameController gameController;
    private final HashMap<KeyCode, Boolean> keyMap = new HashMap<>();
    private final AnimationTimer gameTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            updateGame();
        }
    };
    private MediaPlayer backgroundMusic;

    private void updateGame() {
        if (isPressed(KeyCode.A))
            gameController.movePlayerX(-5);
        if (isPressed(KeyCode.D))
            gameController.movePlayerX(5);
        if (isPressed(KeyCode.W))
            gameController.movePlayerY(-5);
        if (isPressed(KeyCode.S))
            gameController.movePlayerY(5);
        if (isPressed(KeyCode.ESCAPE)) {
            gameTimer.stop();
            gameController.pause();
        }
    }

    private boolean isPressed(KeyCode key) {
        return keyMap.getOrDefault(key, false);
    }

    public void resumeGame() {
        gameTimer.start();
    }

    public void loadPage(String path) {
        FXMLLoader pageLoader = new FXMLLoader(getClass().getResource(path));
        try {appRoot.setRoot(pageLoader.load());}
        catch (IOException e) {throw new RuntimeException(e);}
        viewController = pageLoader.getController();
        viewController.setGameEngine(this);

        gameTimer.stop();
        if (backgroundMusic != null) backgroundMusic.stop();
    }

    public void loadGame() {
        loadPage("view/game-view.fxml");
        gameController = (GameController) viewController;
        gameTimer.start();
        backgroundMusic = GameResource.loadSound(gameController.getWorld().getMusic());
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
    }

    public void exit() {
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader pageLoader = new FXMLLoader(getClass().getResource("view/start-page-view.fxml"));
        appRoot = new Scene(pageLoader.load());
        viewController = pageLoader.getController();
        viewController.setGameEngine(this);

        appRoot.setOnKeyPressed(event -> keyMap.put(event.getCode(), true));
        appRoot.setOnKeyReleased(event -> keyMap.put(event.getCode(), false));

        stage.setTitle("Cavern Warrior");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(appRoot);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
