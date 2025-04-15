package com.saulms.verdantrealm.controllers;

import com.saulms.verdantrealm.Camera;
import com.saulms.verdantrealm.data.GameResource;
import com.saulms.verdantrealm.data.SoundManager;
import com.saulms.verdantrealm.entities.Direction;
import com.saulms.verdantrealm.weapons.WeaponData;
import com.saulms.verdantrealm.world.World;
import com.saulms.verdantrealm.entities.Enemy;
import com.saulms.verdantrealm.entities.Entity;
import com.saulms.verdantrealm.entities.Player;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

public class GameController extends Controller {

    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

    private World world;
    private Player player;
    private Camera camera;
    private final List<Entity> entities = new ArrayList<>();

    @FXML private Pane root;
    @FXML private Pane mapPane;
    @FXML private Label fpsLabel;
    @FXML private BorderPane pausePane;
    @FXML private Label resumeLabel;
    @FXML private Label backLabel;

    @FXML @Override
    protected void initialize() {
        camera = new Camera(0, 0);
        loadWorld();
        loadPlayer();
        loadEnemies();
        WeaponData.load();
        player.setWeapon(WeaponData.createMelee(WeaponData.Melee.DAGGER, 1));
        SoundManager.playMusic(world.getMusic(), true);

        pausePane.setMinWidth(SCREEN_WIDTH);
        pausePane.setMinHeight(SCREEN_HEIGHT);
        setHoverAnimation(resumeLabel);
        setHoverAnimation(backLabel);
    }

    private void loadWorld() {
        world = GameResource.loadJson("world/test_world.json", World.class);
        Image tileset = GameResource.loadTilemap(world.getTileset());
        List<List<Integer>> tileMap = world.getTilemap();
        int tileSize = world.getTilesize();
        for (int y = 0; y < tileMap.size(); y++) {
            for (int x = 0; x < tileMap.getFirst().size(); x++) {
                int type = tileMap.get(y).get(x);
                ImageView tileView = new ImageView(tileset);
                tileView.setViewport(new Rectangle2D(tileSize * type, 0, tileSize, tileSize));
                tileView.setLayoutX(x * tileSize);
                tileView.setLayoutY(y * tileSize);
                mapPane.getChildren().add(tileView);
            }
        }
    }

    private void loadPlayer() {
        player = new Player(world.getSpawnX(), world.getSpawnY(), 100, Direction.RIGHT);
        entities.add(player);
        root.getChildren().add(1, player.getSprite());
    }

    private void loadEnemies() {
        for (Enemy enemy : world.getEnemies()) {
            enemy.initialize();
            entities.add(enemy);
            mapPane.getChildren().add(enemy.getSprite());
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
        SoundManager.stopMusic();
        gameEngine.loadPage("view/start-page-view.fxml");
    }

    public void updateFPS(double fps) {
        fpsLabel.setText(" FPS: %.1f".formatted(fps));
    }

    public void attack() {
        for (Entity enemy : entities) {
            if (enemy instanceof Enemy)
                if (intersects(enemy, player.getWeapon().getHitBox()))
                    enemy.setHealthPoints(enemy.getHealthPoints() - 10);
        }
    }

    public void movePlayerX(int dx) {
        moveEntityX(player, dx);
    }

    public void movePlayerY(int dy) {
        moveEntityY(player, dy);
    }

    private void moveEntityX(Entity entity, int dx) {
        boolean movingRight = dx > 0;
        entity.setDirection(movingRight ? Direction.RIGHT : Direction.LEFT);
        for (int i = 0; i < Math.abs(dx); i++) {
            if (checkWorldCollisionX(entity, movingRight)) return;
            if (checkEntityCollisionX(entity, movingRight)) return;

            entity.setX(entity.getX() + (movingRight ? 1 : -1));
            if (entity instanceof Player) scrollX(movingRight);
            else entity.getSprite().setLayoutX(entity.getX());
        }
    }

    private boolean checkWorldCollisionX(Entity entity, boolean movingRight) {
        int tileSize = world.getTilesize();
        for (int y = 0; y < world.getCollision().size(); y++) {
            for (int x = 0; x < world.getCollision().getFirst().size(); x++) {
                if (world.getCollision().get(y).get(x) == 0) continue;

                boolean touchingWall = intersects(entity, x * tileSize, y * tileSize, tileSize);
                boolean besideWall = entity.getBottom() == y * tileSize || entity.getTop() == (y + 1) * tileSize;
                if (touchingWall == besideWall) continue;

                boolean collideRight = movingRight && entity.getRight() == x * tileSize;
                boolean collideLeft = !movingRight && entity.getLeft() == (x + 1) * tileSize;
                if (collideRight || collideLeft) return true;
            }
        }
        return false;
    }

    private boolean checkEntityCollisionX(Entity entity, boolean movingRight) {
        for (Entity e : entities) {
            if (entity == e) continue;

            boolean touchingEntity = intersects(entity, e);
            boolean besideEntity = entity.getBottom() == e.getTop() || entity.getTop() == e.getBottom();
            if (touchingEntity == besideEntity) continue;

            boolean collideRight = movingRight && entity.getRight() == e.getLeft();
            boolean collideLeft = !movingRight && entity.getLeft() == e.getRight();
            if (collideRight || collideLeft) return true;
        }
        return false;
    }

    private void scrollX(boolean movingRight) {
        int playerX = player.getX();
        int cameraX = camera.getX();
        boolean scrollRight = playerX - cameraX > 2 * SCREEN_WIDTH / 3 && playerX < mapPane.getWidth() - SCREEN_WIDTH / 3;
        boolean scrollLeft = playerX - cameraX < SCREEN_WIDTH / 3 && playerX > SCREEN_WIDTH / 3;

        if (movingRight ? scrollRight : scrollLeft)
            camera.setX(cameraX + (movingRight ? 1 : -1));
        else
            player.getSprite().setLayoutX(playerX - cameraX);
        mapPane.setLayoutX(-cameraX);
    }

    private void moveEntityY(Entity entity, int dy) {
        boolean movingDown = dy > 0;
        player.setDirection(movingDown ? Direction.DOWN : Direction.UP);
        for (int i = 0; i < Math.abs(dy); i++) {
            if (checkWorldCollisionY(entity, movingDown)) return;
            if (checkEntityCollisionY(entity, movingDown)) return;

            entity.setY(entity.getY() + (movingDown ? 1 : -1));
            if (entity instanceof Player) scrollY(movingDown);
            else entity.getSprite().setLayoutY(entity.getY());
        }
    }

    private boolean checkWorldCollisionY(Entity entity, boolean movingDown) {
        int tileSize = world.getTilesize();
        for (int y = 0; y < world.getCollision().size(); y++) {
            for (int x = 0; x < world.getCollision().getFirst().size(); x++) {
                if (world.getCollision().get(y).get(x) == 0) continue;

                boolean touchingWall = intersects(entity, x * tileSize, y * tileSize, tileSize);
                boolean besideWall = entity.getRight() == x * tileSize || entity.getLeft() == (x + 1) * tileSize;
                if (touchingWall == besideWall) continue;

                boolean collideDown = movingDown && entity.getBottom() == y * tileSize;
                boolean collideUp = !movingDown && entity.getTop() == (y + 1) * tileSize;
                if (collideDown || collideUp) return true;
            }
        }
        return false;
    }

    private boolean checkEntityCollisionY(Entity entity, boolean movingDown) {
        for (Entity e : entities) {
            if (entity == e) continue;

            boolean touchingEntity = intersects(entity, e);
            boolean besideEntity = entity.getRight() == e.getLeft() || entity.getLeft() == e.getRight();
            if (touchingEntity == besideEntity) continue;

            boolean collideDown = movingDown && entity.getBottom() == e.getTop();
            boolean collideUp = !movingDown && entity.getTop() == e.getBottom();
            if (collideDown || collideUp) return true;
        }
        return false;
    }

    private void scrollY(boolean movingDown) {
        int playerY = player.getY();
        int cameraY = camera.getY();
        boolean scrollDown = playerY - cameraY > 2 * SCREEN_HEIGHT / 3 && playerY < mapPane.getHeight() - SCREEN_HEIGHT / 3;
        boolean scrollUp = playerY - cameraY < SCREEN_HEIGHT / 3 && playerY > SCREEN_HEIGHT / 3;

        if (movingDown ? scrollDown : scrollUp)
            camera.setY(cameraY + (movingDown ? 1 : -1));
        else
            player.getSprite().setLayoutY(playerY - cameraY);
        mapPane.setLayoutY(-cameraY);
    }

    private boolean intersects(Entity entity, int tileX, int tileY, int tileSize) {
        return entity.getRight() >= tileX &&
                entity.getLeft() <= tileX + tileSize &&
                entity.getBottom() >= tileY &&
                entity.getTop() <= tileY + tileSize;
    }

    private boolean intersects(Entity entity1, Entity entity2) {
        return entity1.getRight() >= entity2.getLeft() &&
                entity1.getLeft() <= entity2.getRight() &&
                entity1.getBottom() >= entity2.getTop() &&
                entity1.getTop() <= entity2.getBottom();
    }

    private boolean intersects(Entity entity, Rectangle2D rect) {
        return entity.getRight() >= rect.getMinX() &&
                entity.getLeft() <= rect.getMaxX() &&
                entity.getBottom() >= rect.getMinY() &&
                entity.getTop() <= rect.getMaxY();
    }

}
