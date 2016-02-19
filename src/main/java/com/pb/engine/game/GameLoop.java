package com.pb.engine.game;

import com.pb.engine.graphics.window.Window;
import com.pb.engine.utils.Configuration;
import com.pb.engine.utils.Time;

public class GameLoop {

    private Game game;
    private Configuration gameConfig;

    private int frames = 0;
    private static int currentFPS;

    public void start(Game game, Configuration gameConfig) {
        this.game = game;
        this.gameConfig = gameConfig;

        Window.createWindow(gameConfig.width, gameConfig.height, gameConfig.title, false);

        game.config(gameConfig);
        game.initGL();
        game.init();

        this.run();
    }

    private void run() {
        frames = 0;
        int frameCounter = 0;

        double frameTime = 1.0 / gameConfig.targetFPS;

        long lastTime = Time.getTime();
        double unprocessed = 0.0;

        while (!Window.isWindowCloseRequested()) {
            boolean render = false;
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessed += passedTime / (double) Time.SECOND;
            frameCounter += passedTime;

            while (unprocessed > frameTime) {
                render = true;
                unprocessed -= frameTime;

                if (Window.isWindowCloseRequested()) {
                    stop();
                    break;
                }

                Time.setDelta(frameTime);
                update();

                if (frameCounter >= Time.SECOND) {
                    if (gameConfig.debug)
                        System.out.println("FPS: " + frames);

                    currentFPS = frames;
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        stop();
    }

    private void update() {
        Window.update();
        game.update();
    }

    private void render() {
        game.render();
    }

    public void stop() {
        game.cleanup();
        game = null;
        Window.dispose();
    }

    public static int getFPS() {
        return currentFPS;
    }

}

