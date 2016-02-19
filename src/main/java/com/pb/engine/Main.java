package com.pb.engine;

import com.pb.engine.game.BlockGame;
import com.pb.engine.game.Game;
import com.pb.engine.game.GameLoop;
import com.pb.engine.utils.Configuration;

public class Main {

    private GameLoop gameLoop;

    public Main(String[] runtimeArgs) {
        try {
            Configuration config = new Configuration();
            config.title = "PB Game";
            config.width = 1200;
            config.height = 900;
            config.targetFPS = 120;
            config.targetUPS = 60;
            config.vSync = true;

            Game game = new BlockGame();

            gameLoop = new GameLoop();
            gameLoop.start(game, config);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
