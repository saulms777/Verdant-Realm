package com.saulms.cavernwarrior.controllers;

import com.saulms.cavernwarrior.Camera;
import com.saulms.cavernwarrior.GameResource;
import com.saulms.cavernwarrior.World;
import com.saulms.cavernwarrior.entities.Entity;
import com.saulms.cavernwarrior.entities.Player;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import java.util.List;

public class GameController extends Controller {

    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private World world;
    private Player player;
    private Camera camera;

    @FXML private Pane root;
    @FXML private GridPane gridPane;
    @FXML private BorderPane pausePane;
    @FXML private Label resumeLabel;
    @FXML private Label backLabel;

    @FXML
    @Override
    protected void initialize() {
        camera = new Camera(0, 0);
        loadWorld();
        player = new Player(world.getSpawnX(), world.getSpawnY());
        root.getChildren().add(1, player.getSprite());

        pausePane.setMinWidth(SCREEN_WIDTH);
        pausePane.setMinHeight(SCREEN_HEIGHT);
        setHoverAnimation(resumeLabel);
        setHoverAnimation(backLabel);
    }

    private void loadWorld() {
        world = GameResource.loadJson("test_world.json", World.class);
        Image tileset = new Image(GameResource.loadImage(world.getTileset()));
        List<List<Integer>> tileMap = world.getTilemap();
        int tileSize = world.getTilesize();
        for (int y = 0; y < tileMap.size(); y++) {
            for (int x = 0; x < tileMap.getFirst().size(); x++) {
                int type = tileMap.get(y).get(x);
                ImageView tileView = new ImageView(tileset);
                tileView.setSmooth(false);
                tileView.setViewport(new Rectangle2D(tileSize * type, 0, tileSize, tileSize));
                gridPane.add(tileView, tileSize * x, tileSize * y);
            }
        }
    }

    public void pause() {
        pausePane.setVisible(true);
    }

    @FXML
    private void resume() {
        pausePane.setVisible(false);
        gameEngine.resumeGame();
    }

    @FXML
    private void back() {
        gameEngine.loadPage("view/start-page-view.fxml");
    }

    public void movePlayerX(int dx) {
        moveEntityX(player, dx);
    }

    public void movePlayerY(int dy) {
        moveEntityY(player, dy);
    }

    private void moveEntityX(Entity entity, int dx) {
        List<List<Integer>> collisionMap = world.getCollision();
        int tileSize = world.getTilesize();

        boolean movingRight = dx > 0;
        for (int i = 0; i < Math.abs(dx); i++) {
            for (int y = 0; y < collisionMap.size(); y++) {
                for (int x = 0; x < collisionMap.getFirst().size(); x++) {
                    if (collisionMap.get(y).get(x) == 0) continue;

                    boolean touchingWall = intersects(entity, x * tileSize, y * tileSize, tileSize);
                    boolean besideWall = entity.getBottom() == y * tileSize || entity.getTop() == (y + 1) * tileSize;
                    if (touchingWall == besideWall) continue;

                    boolean collideRight = movingRight && player.getRight() == x * tileSize;
                    boolean collideLeft = !movingRight && player.getLeft() == (x + 1) * tileSize;
                    if (collideRight || collideLeft) return;
                }
            }

            entity.setX(entity.getX() + (movingRight ? 1 : -1));
            if (entity instanceof Player) scrollX(movingRight);
        }
    }

    private void scrollX(boolean movingRight) {
        int playerX = player.getX();
        int cameraX = camera.getX();
        boolean scrollRight = playerX - cameraX > 2 * SCREEN_WIDTH / 3 && playerX < gridPane.getWidth() - SCREEN_WIDTH / 3;
        boolean scrollLeft = playerX - cameraX < SCREEN_WIDTH / 3 && playerX > SCREEN_WIDTH / 3;

        if (movingRight ? scrollRight : scrollLeft)
            camera.setX(cameraX + (movingRight ? 1 : -1));
        else
            player.getSprite().setLayoutX(playerX - cameraX);
        gridPane.setLayoutX(-cameraX);
    }

    private void moveEntityY(Entity entity, int dy) {
        List<List<Integer>> collisionMap = world.getCollision();
        int tileSize = world.getTilesize();

        boolean movingDown = dy > 0;
        for (int i = 0; i < Math.abs(dy); i++) {
            for (int y = 0; y < collisionMap.size(); y++) {
                for (int x = 0; x < collisionMap.getFirst().size(); x++) {
                    if (collisionMap.get(y).get(x) == 0) continue;

                    boolean touchingWall = intersects(entity, x * tileSize, y * tileSize, tileSize);
                    boolean besideWall = entity.getRight() == x * tileSize || entity.getLeft() == (x + 1) * tileSize;
                    if (touchingWall == besideWall) continue;

                    boolean collideDown = movingDown && entity.getBottom() == y * tileSize;
                    boolean collideUp = !movingDown && entity.getTop() == (y + 1) * tileSize;
                    if (collideDown || collideUp) return;
                }
            }

            entity.setY(entity.getY() + (movingDown ? 1 : -1));
            if (entity instanceof Player) scrollY(movingDown);
        }
    }

    private void scrollY(boolean movingDown) {
        int playerY = player.getY();
        int cameraY = camera.getY();
        boolean scrollDown = playerY - cameraY > 2 * SCREEN_HEIGHT / 3 && playerY < gridPane.getHeight() - SCREEN_HEIGHT / 3;
        boolean scrollUp = playerY - cameraY < SCREEN_HEIGHT / 3 && playerY > SCREEN_HEIGHT / 3;

        if (movingDown ? scrollDown : scrollUp)
            camera.setY(cameraY + (movingDown ? 1 : -1));
        else
            player.getSprite().setLayoutY(playerY - cameraY);
        gridPane.setLayoutY(-cameraY);
    }

    private boolean intersects(Entity entity, int tileX, int tileY, int tileSize) {
        return entity.getRight() >= tileX &&
                entity.getLeft() <= tileX + tileSize &&
                entity.getBottom() >= tileY &&
                entity.getTop() <= tileY + tileSize;
    }

}
