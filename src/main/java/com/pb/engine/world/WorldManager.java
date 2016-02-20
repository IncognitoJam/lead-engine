package com.pb.engine.world;

import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.graphics.shader.Shader;
import com.pb.engine.graphics.shader.ShaderProgram;
import com.pb.engine.maths.Vector3i;
import com.pb.engine.world.generation.WorldGenerator;

import java.util.ArrayList;

public class WorldManager {

    private ArrayList<Chunk> loadedChunks;
    private Chunk[] activeChunks;

    private ShaderProgram shader;

    public WorldManager() {
        initGL();
        init();

        createWorld();
    }

    private void initGL() {
        Spritesheet.blocks.bind();
        Shader temp = new Shader("/shaders/chunk.vert", "/shaders/chunk.frag");
        shader = new ShaderProgram(temp.getvShader(), temp.getfShader());
    }

    private void init() {
        WorldGenerator.initialize("JAM".hashCode());

        loadedChunks = new ArrayList<>();
        activeChunks = new Chunk[World.VIEW_DISTANCE * World.VIEW_DISTANCE * World.VIEW_DISTANCE];
    }

    private void createWorld() {
        for (int i = 0; i < World.VIEW_DISTANCE * World.VIEW_DISTANCE * World.VIEW_DISTANCE; i++) {
            activeChunks[i] = new Chunk(shader, Vector3i.chunkCoordinate(i));
            System.out.println("Created chunk " + Vector3i.chunkCoordinate(i).toString());
        }
    }

    public void update() {
        for (int i = 0; i < World.VIEW_DISTANCE * World.VIEW_DISTANCE * World.VIEW_DISTANCE; i++) {
            activeChunks[i].update();
        }
    }

    public void render() {
        for (int i = 0; i < World.VIEW_DISTANCE * World.VIEW_DISTANCE * World.VIEW_DISTANCE; i++) {
            activeChunks[i].render();
        }
    }

    public void cleanup() {
        for (int i = 0; i < World.VIEW_DISTANCE * World.VIEW_DISTANCE * World.VIEW_DISTANCE; i++) {
            activeChunks[i].cleanup();
        }
        Spritesheet.blocks.cleanup();
        shader.cleanup();
    }

}
