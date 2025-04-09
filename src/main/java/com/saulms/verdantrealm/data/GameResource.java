package com.saulms.verdantrealm.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulms.verdantrealm.GameEngine;
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
        return Objects.requireNonNull(GameEngine.class.getResourceAsStream(path));
    }

    public static Image loadImage(String path) {
        path = "/com/saulms/verdantrealm/images/" + path;
        return new Image(getResource(path));
    }

    public static <T> T loadJson(String path, Class<T> type) {
        path = "/com/saulms/verdantrealm/data/" + path;
        try {return mapper.readValue(getResource(path), type);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    public static MediaPlayer loadSound(String path) {
        path = "/com/saulms/verdantrealm/sounds/" + path;
        URL resource = Objects.requireNonNull(GameEngine.class.getResource(path));
        return new MediaPlayer(new Media(resource.toString()));
    }

}
