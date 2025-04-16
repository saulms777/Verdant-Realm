package com.saulms.verdantrealm.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class GameResource {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static InputStream getResource(String path) {
        return Objects.requireNonNull(GameResource.class.getResourceAsStream(path));
    }

    public static Image loadTilemap(String path) {
        path = "/com/saulms/verdantrealm/assets/world/" + path;
        return new Image(getResource(path));
    }

    public static Image loadPlayer(String path) {
        path = "/com/saulms/verdantrealm/assets/player/" + path;
        return new Image(getResource(path));
    }

    public static Image loadEnemy(String path) {
        path = "/com/saulms/verdantrealm/assets/enemies/" + path;
        return new Image(getResource(path));
    }

    public static <T> T loadJson(String path, Class<T> type) {
        path = "/com/saulms/verdantrealm/data/" + path;
        try {return mapper.readValue(getResource(path), type);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    public static <T> T loadJson(String path, TypeReference<T> type) {
        path = "/com/saulms/verdantrealm/data/" + path;
        try {return mapper.readValue(getResource(path), type);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    public static MediaPlayer loadSound(String path) {
        path = "/com/saulms/verdantrealm/sounds/" + path;
        URL resource = Objects.requireNonNull(GameResource.class.getResource(path));
        return new MediaPlayer(new Media(resource.toString()));
    }

}
