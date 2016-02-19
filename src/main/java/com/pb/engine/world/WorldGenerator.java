package com.pb.engine.world;

import com.pb.engine.maths.Vector3f;

public interface WorldGenerator {

    void setSeed(String seed);

    String getSeed();

    byte getBlock(Vector3f location);

}
