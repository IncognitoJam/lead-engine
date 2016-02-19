package com.pb.engine.world;

import com.pb.engine.maths.Vector3f;

import java.util.Random;

public class TestWorldGenerator implements WorldGenerator {

    private final Random random = new Random();
    private String baseSeed;

    @Override
    public void setSeed(String seed) {
        this.baseSeed = seed;
    }

    @Override
    public String getSeed() {
        return baseSeed;
    }

    @Override
    public byte getBlock(Vector3f location) {
        random.setSeed((String.valueOf(baseSeed.hashCode()) + String.valueOf(location.hashCode())).hashCode());

        double value = random.nextDouble();
        return (byte) value;
    }

}
