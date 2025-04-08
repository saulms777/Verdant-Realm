package com.saulms.cavernwarrior.controllers;

import com.saulms.cavernwarrior.Camera;
import com.saulms.cavernwarrior.GameResource;
import com.saulms.cavernwarrior.World;
import com.saulms.cavernwarrior.entities.Player;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
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
        for (int y = 0; y < tileMap.size(); y++) {
            for (int x = 0; x < tileMap.getFirst().size(); x++) {
                int type = tileMap.get(y).get(x);
                ImageView tileView = new ImageView(tileset);
                tileView.setSmooth(false);
                tileView.setViewport(new Rectangle2D(144 * type, 0, 144, 144));
                gridPane.add(tileView, 144 * x, 144 * y);
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

    public void movePlayerX(double dx) {
        double originalX = player.getX();
        moveLayoutPlayerX(dx);
        CollisionResult result = checkCollision();
        if (!result.collided) return;

        player.setX(originalX);
        double tileSize = world.getTilesize();
        for (int step = 0; step < Math.abs(dx); step++) {
            moveLayoutPlayerX(dx / 5);
            Bounds bounds = player.getSprite().getBoundsInParent();
            if (bounds.intersects(result.x * tileSize, result.y * tileSize, tileSize, tileSize)) {
                moveLayoutPlayerX(-dx / 5);
                break;
            }
        }
    }

    public void movePlayerY(double dy) {
        double originalY = player.getY();
        moveLayoutPlayerY(dy);
        CollisionResult result = checkCollision();
        if (!result.collided) return;

        player.setY(originalY);
        double tileSize = world.getTilesize();
        for (int step = 0; step < Math.abs(dy); step++) {
            moveLayoutPlayerY(dy / 5);
            Bounds bounds = player.getSprite().getBoundsInParent();
            if (bounds.intersects(result.x * tileSize, result.y * tileSize, tileSize, tileSize)) {
                moveLayoutPlayerY(-dy / 5);
                break;
            }
        }
    }

    private void moveLayoutPlayerX(double dx) {
        double x = player.getX() + dx;
        double cameraX = camera.getX();
        player.setX(x);

        boolean movingRight = dx >= 0;
        boolean scrollRight = x - cameraX > 2 * SCREEN_WIDTH / 3 && x < gridPane.getWidth() - SCREEN_WIDTH / 3;
        boolean scrollLeft = x - cameraX < SCREEN_WIDTH / 3 && x > SCREEN_WIDTH / 3;
        if (movingRight ? scrollRight : scrollLeft) camera.setX(cameraX + dx);
        else player.getSprite().setLayoutX(x - cameraX);
        gridPane.setLayoutX(-cameraX);
    }

    private void moveLayoutPlayerY(double dy) {
        double y = player.getY() + dy;
        double cameraY = camera.getY();
        player.setY(y);

        boolean movingDown = dy >= 0;
        boolean scrollDown = y - cameraY > 2 * SCREEN_HEIGHT / 3 && y < gridPane.getHeight() - SCREEN_HEIGHT / 3;
        boolean scrollUp = y - cameraY < SCREEN_HEIGHT / 3 && y > SCREEN_HEIGHT / 3;
        if (movingDown ? scrollDown : scrollUp) camera.setY(cameraY + dy);
        else player.getSprite().setLayoutY(y - cameraY);
        gridPane.setLayoutY(-cameraY);
    }

    private CollisionResult checkCollision() {
        Bounds bounds = player.getSprite().getBoundsInParent();
        List<List<Integer>> collisionMap = world.getCollision();
        double tileSize = world.getTilesize();

        for (int y = 0; y < collisionMap.size(); y++) {
            for (int x = 0; x < collisionMap.getFirst().size(); x++) {
                if (collisionMap.get(y).get(x) == 0) continue;
                if (bounds.intersects(x * tileSize, y * tileSize, tileSize, tileSize)) {
                    return new CollisionResult(true, x, y);
                }
            }
        }
        return new CollisionResult(false, -1, -1);
    }

    private static class CollisionResult {
        public boolean collided;
        public int x, y;

        public CollisionResult(boolean collided, int x, int y) {
            this.collided = collided;
            this.x = x;
            this.y = y;
        }
    }

}
