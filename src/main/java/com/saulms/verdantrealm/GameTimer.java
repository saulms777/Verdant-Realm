package com.saulms.verdantrealm;

import javafx.animation.AnimationTimer;

import java.util.function.Function;

public class GameTimer extends AnimationTimer {

    private final Function<UpdateInput, UpdateOutput> updateFunction;
    private long lastUpdate = 0;
    private int index = 0;
    private final double[] frameRates = new double[100];

    private long lastFpsUpdate = 0;
    private long lastAttack = 0;
    public record UpdateInput(boolean canUpdateFps, boolean canAttack) {}
    public record UpdateOutput(boolean attacked) {}

    public GameTimer(Function<UpdateInput, UpdateOutput> updateFunction) {
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

        boolean canUpdateFps = (now - lastFpsUpdate) >= 250_000_000;
        if (canUpdateFps) lastFpsUpdate = now;
        boolean canAttack = (now - lastAttack) >= 1_000_000_000;

        UpdateOutput out = updateFunction.apply(new UpdateInput(canUpdateFps, canAttack));
        if (out.attacked()) lastAttack = now;
    }

    public double getFPS() {
        double total = 0;
        for (double fps : frameRates) total += fps;
        return total / 100;
    }

}
