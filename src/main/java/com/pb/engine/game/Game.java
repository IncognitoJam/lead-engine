package com.pb.engine.game;

import com.pb.engine.utils.Configuration;

public interface Game {

    void config(Configuration gameConfig);

    void initGL();

    void init();

    void update();

    void render();

    void cleanup();

}
