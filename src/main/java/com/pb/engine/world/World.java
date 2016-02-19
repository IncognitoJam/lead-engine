package com.pb.engine.world;

import com.pb.engine.maths.Vector2f;

import java.util.concurrent.ConcurrentHashMap;

public class World {

    public static final int CHUNK_SIZE = 16;
    public static final int CHUNK_HEIGHT = 512;

    private final String name;
    private final String seed;
    private final long creationTime;

    private final WorldGenerator worldGenerator;

    private ConcurrentHashMap<Vector2f, Chunk> chunkMap = new ConcurrentHashMap<>();

    public World(String name, String seed) {
        this.name = name;
        this.seed = seed;
        this.creationTime = System.currentTimeMillis();
        this.worldGenerator = new TestWorldGenerator();
        this.worldGenerator.setSeed(seed);
    }



}
