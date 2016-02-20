package com.pb.engine.world.generation;

import com.pb.engine.maths.Vector3i;
import com.pb.engine.world.blocks.Block;
import com.pb.engine.world.generation.noise.SimplexNoise;

public class WorldGenerator {

    private static SimplexNoise noise, oreNoise;

    public static void initialize(long seed) {
        noise = new SimplexNoise(100, 0.1, seed);
        oreNoise = new SimplexNoise(100, 1.0, seed * 2);
    }

    public static byte getBlockByte(Vector3i chunkPos, Vector3i blockPos) {
        double noiseValue = noise.getNoise(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        double oreValue = oreNoise.getNoise(blockPos.getX(), -blockPos.getY(), blockPos.getZ());

        byte id;

        if (noiseValue >= 0) {
            id = Block.STONE.getId();
        } else {
            id = Block.AIR.getId();
        }

        if (Math.abs(oreValue) >= 4.0 && id == Block.STONE.getId())
            id = Block.SAND.getId();

        return id;
    }

}
