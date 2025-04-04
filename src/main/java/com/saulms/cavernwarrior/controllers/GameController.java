package com.saulms.cavernwarrior.controllers;

import com.saulms.cavernwarrior.Camera;
import com.saulms.cavernwarrior.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class GameController extends Controller {

    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private Player player;
    private Camera camera;

    @FXML private Pane root;
    @FXML private GridPane gridPane;

    @FXML
    @Override
    protected void initialize() {
        player = new Player(0, 0, 30, 30);
        root.getChildren().add(player.getSprite());
        camera = new Camera(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        System.out.println(SCREEN_WIDTH);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle(200, 200,
                        Color.rgb(255 - 25 * i, 255 - 25 * j, 128));
                rectangle.setSmooth(false);
                gridPane.add(rectangle, i, j);
            }
        }
    }

    public void movePlayerX(double v) {
        double x = player.getX();
        double cameraX = camera.getX();
        double playerWidth = player.getWidth();
        double gridWidth = gridPane.getWidth();

        if (x + v <= 0) x = 0;
        else if (x + playerWidth + v >= gridWidth) x = gridWidth - playerWidth;
        else x += v;
        player.setX(x);

        if (v >= 0 ?
                x - cameraX > 2 * SCREEN_WIDTH / 3 && x < gridWidth - SCREEN_WIDTH / 3 :
                x - cameraX < SCREEN_WIDTH / 3 && x > SCREEN_WIDTH / 3)
            camera.setX(cameraX + v);
        else player.getSprite().setLayoutX(x - cameraX);
        gridPane.setLayoutX(-cameraX);
    }

    public void movePlayerY(double v) {
        double y = player.getY();
        double cameraY = camera.getY();
        double playerHeight = player.getHeight();
        double gridHeight = gridPane.getHeight();

        if (y + v <= 0) y = 0;
        else if (y + playerHeight + v >= gridHeight) y = gridHeight - playerHeight;
        else y += v;
        player.setY(y);

        if (v >= 0 ?
                y - cameraY > 2 * SCREEN_HEIGHT / 3 && y < gridHeight - SCREEN_HEIGHT / 3 :
                y - cameraY < SCREEN_HEIGHT / 3 && y > SCREEN_HEIGHT / 3)
            camera.setY(cameraY + v);
        else player.getSprite().setLayoutY(y - cameraY);
        gridPane.setLayoutY(-cameraY);
    }

}
