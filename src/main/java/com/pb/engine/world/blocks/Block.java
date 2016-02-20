package com.pb.engine.world.blocks;

import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.maths.Color4f;

import java.util.HashMap;

public class Block {

    private static HashMap<Byte, Block> blocks = new HashMap<>();

    public static Block AIR = new BlockAir((byte) 0);
    public static Block STONE = new BlockStone((byte) 1);
    public static Block GRASS = new BlockGrass((byte) 2);
    public static Block DIRT = new BlockDirt((byte) 3);
    public static Block SAND = new BlockSand((byte) 4);

    private byte id;
    private Color4f color = Color4f.WHITE;

    public Block(byte id) {
        this.id = id;

        if (blocks.containsKey(id))
            throw new RuntimeException("Two blocks with the same ID! (" + id + ")");
        blocks.put(id, this);
    }

    public static Block getBlock(byte id) {
        if (blocks.containsKey(id))
            return blocks.get(id);
        else
            return Block.AIR;
    }

    public static Color4f getColor(byte id) {
        return getBlock(id).getColor();
    }

    public static float[] getTexCoords(byte blockId) {
        int id = blockId & 0xFF;
        float temp = id * Spritesheet.blocks.uniformSize();
        float x = (float) Math.floor(temp / Spritesheet.blocks.uniformSize()) * Spritesheet.blocks.uniformSize();
        float y = (float) (temp % (Math.pow(Spritesheet.blocks.uniformSize(), 2)));
        return new float[]{x, y};
    }

    public byte getId() {
        return id;
    }

    public Block setColor(Color4f color) {
        this.color = color;
        return this;
    }

    public Color4f getColor() {
        return color;
    }

}
