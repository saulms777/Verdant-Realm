package com.saulms.cavernwarrior.controllers;

import com.saulms.cavernwarrior.Player;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class GameController extends Controller {

    private Player player;

    @FXML private Pane root;
    @FXML private GridPane gridPane;

    @FXML
    @Override
    protected void initialize() {
        player = new Player(30, 30);
        root.getChildren().add(player.getSprite());

        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 80; j++) {
                Rectangle rectangle = new Rectangle(20, 20,
                        Color.rgb(255 - 3 * i, 255 - 3 * j, 128));
                rectangle.setSmooth(false);
                gridPane.add(rectangle, i, j);
            }
        }
    }

    public void movePlayerX(double v) {
        double x = player.getX();
        double playerWidth = player.getWidth();
        double gridWidth = gridPane.getWidth();
        double screenWidth = Screen.getPrimary().getBounds().getWidth();

        if (x + v <= 0) x = 0;
        else if (x + playerWidth + v >= gridWidth) x = gridWidth - playerWidth;
        else x += v;
        player.setX(x);

        if (x <= screenWidth / 2) {
            player.setLayoutX(x);
            gridPane.setLayoutX(0);
        } else if (x >= gridWidth - screenWidth / 2) {
            player.setLayoutX(x - (gridWidth - screenWidth));
            gridPane.setLayoutX(screenWidth - gridWidth);
        } else {
            gridPane.setLayoutX(gridPane.getLayoutX() - v);
        }
    }

    public void movePlayerY(double v) {
        double y = player.getY();
        double playerHeight = player.getHeight();
        double gridHeight = gridPane.getHeight();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        if (y + v <= 0) y = 0;
        else if (y + playerHeight + v >= gridHeight) y = gridHeight - playerHeight;
        else y += v;
        player.setY(y);

        if (y <= screenHeight / 2) {
            player.setLayoutY(y);
            gridPane.setLayoutY(0);
        } else if (y >= gridHeight - screenHeight / 2) {
            player.setLayoutY(y - (gridHeight - screenHeight));
            gridPane.setLayoutY(screenHeight - gridHeight);
        } else {
            gridPane.setLayoutY(gridPane.getLayoutY() - v);
        }
    }

}
