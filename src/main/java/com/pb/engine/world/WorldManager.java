package com.pb.engine.world;

import java.util.ArrayList;

public class WorldManager {

    private static ArrayList<String> worlds = new ArrayList<>();
    private static World currentWorld;

    public static boolean worldExists(String world) {
        return world.toLowerCase().equals("world");
    }

    public static World getWorld(String world) {
        return null;
    }

}
