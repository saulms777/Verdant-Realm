package com.saulms.verdantrealm;

import javafx.animation.AnimationTimer;

import java.util.function.Consumer;

public class GameTimer extends AnimationTimer {

    private long lastUpdate = 0;
    private long lastFpsUpdate = 0;
    private int index = 0;
    private final double[] frameRates = new double[100];
    private final Consumer<Boolean> updateFunction;

    public GameTimer(Consumer<Boolean> updateFunction) {
        this.updateFunction = updateFunction;
    }

    @Override
    public void handle(long now) {
        if (lastUpdate > 0) {
            double fps = 1_000_000_000.0 / (now - lastUpdate);
            index %= 100;
            frameRates[index++] = fps;
        }
        lastUpdate = now;

        boolean fpsUpdate = (now - lastFpsUpdate) >= 250_000_000;
        if (fpsUpdate) lastFpsUpdate = now;

        updateFunction.accept(fpsUpdate);
    }

    public double getFPS() {
        double total = 0;
        for (double fps : frameRates) total += fps;
        return total / 100;
    }

}
