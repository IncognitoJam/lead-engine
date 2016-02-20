package com.pb.engine.world;

import com.pb.engine.maths.Vector3i;

import java.util.concurrent.ConcurrentHashMap;

public class World {

    public static final int CHUNK_SIZE = 16;
    public static final int VIEW_DISTANCE = 1;

    private final String seed;

    private ConcurrentHashMap<Vector3i, Chunk> chunkMap = new ConcurrentHashMap<>();

    public World(String seed) {
        this.seed = seed;
    }

    public static Vector3i iToCoords(int i) {

    }

    public static int chunkCoordsToI(int x, int y, int z) {
        return (x * World.VIEW_DISTANCE * World.VIEW_DISTANCE) + (y * World.VIEW_DISTANCE) + z;
    }

    public static int blockCoordsToI(int x, int y, int z) {
        return (x * World.CHUNK_SIZE * World.CHUNK_SIZE) + (y * World.CHUNK_SIZE) + z;
    }

}
