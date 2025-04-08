package com.saulms.cavernwarrior.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saulms.cavernwarrior.GameEngine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class GameResource {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static InputStream getResource(String path) {
        return Objects.requireNonNull(GameEngine.class.getResourceAsStream(path));
    }

    public static InputStream loadImage(String path) {
        path = "/com/saulms/cavernwarrior/images/" + path;
        return getResource(path);
    }

    public static <T> T loadJson(String path, Class<T> type) {
        path = "/com/saulms/cavernwarrior/data/" + path;
        try {return mapper.readValue(getResource(path), type);}
        catch (IOException e) {throw new RuntimeException(e);}
    }

}
