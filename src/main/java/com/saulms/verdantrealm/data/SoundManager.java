package com.saulms.verdantrealm.data;

import javafx.scene.media.MediaPlayer;

import java.util.HashMap;

public class SoundManager {

    private static final HashMap<String, MediaPlayer> musicPlayers = new HashMap<>();
    private static final HashMap<String, MediaPlayer> soundEffects = new HashMap<>();
    private static MediaPlayer currentMusic;

    public static void playMusic(String path, boolean loop) {
        if (!musicPlayers.containsKey(path))
            musicPlayers.put(path, GameResource.loadSound("music/" + path));
        stopMusic();
        currentMusic = musicPlayers.get(path);
        if (loop) currentMusic.setCycleCount(MediaPlayer.INDEFINITE);
        currentMusic.play();
    }

    public static void stopMusic() {
        if (currentMusic == null) return;
        currentMusic.stop();
        currentMusic = null;
    }

    public static void playSfx(String path) {
        if (!soundEffects.containsKey(path))
            soundEffects.put(path, GameResource.loadSound("sfx/" + path));
        MediaPlayer sound = soundEffects.get(path);
        sound.play();
    }

}
