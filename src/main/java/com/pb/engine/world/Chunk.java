package com.pb.engine.world;

import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.graphics.shader.ShaderProgram;
import com.pb.engine.maths.Vector3i;
import com.pb.engine.maths.geometry.Shape;
import com.pb.engine.world.blocks.Block;
import com.pb.engine.world.generation.WorldGenerator;

import static org.lwjgl.opengl.GL11.*;

public class Chunk {

    private ShaderProgram shader;

    private Vector3i pos;
    private byte[] blocks;

    private int vcID;

    public Chunk(ShaderProgram shader, Vector3i pos) {
        this.shader = shader;
        this.pos = pos;

        initGL();
        init();
    }

    public void initGL() {
        vcID = glGenLists(1);

        blocks = new byte[World.CHUNK_SIZE * World.CHUNK_SIZE * World.CHUNK_SIZE];

        createChunk();
        rebuild();
    }

    public void init() {

    }

    public void createChunk() {
        for (int i = 0; i < World.CHUNK_SIZE * World.CHUNK_SIZE * World.CHUNK_SIZE; i++) {
            blocks[i] = WorldGenerator.getBlockByte(pos, Vector3i.blockCoordinate(i));
        }
    }

    public void update() {

    }

    public void render() {
        Spritesheet.blocks.bind();
        glCallList(vcID);
        Spritesheet.blocks.unbind();
    }

    public void rebuild() {
        glNewList(vcID, GL_COMPILE);
        glBegin(GL_QUADS);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        for (int i = 0; i < World.CHUNK_SIZE * World.CHUNK_SIZE * World.CHUNK_SIZE; i++) {
            Vector3i blockPos = Vector3i.blockCoordinate(i);

            if (blockVisible(blockPos.getX(), blockPos.getY(), blockPos.getZ()))
                Shape.createCube(
                        blockPos.getX() + (pos.getX() * World.CHUNK_SIZE),
                        blockPos.getY() + (pos.getY() * World.CHUNK_SIZE),
                        blockPos.getZ() + (pos.getZ() * World.CHUNK_SIZE), blocks[i], 1f);
        }

        glEnd();
        glEndList();
    }

    public void cleanup() {
        glDeleteLists(vcID, 1);
    }

    private boolean blockVisible(int x, int y, int z) {
        return getBlock(x, y + 1, z) == Block.AIR.getId()
                || getBlock(x, y - 1, z) == Block.AIR.getId()
                || getBlock(x + 1, y, z) == Block.AIR.getId()
                || getBlock(x - 1, y, z) == Block.AIR.getId()
                || getBlock(x, y, z + 1) == Block.AIR.getId()
                || getBlock(x, y, z - 1) == Block.AIR.getId();
    }

    public byte[] getBlocks() {
        return blocks;
    }

    public byte getBlock(int x, int y, int z) {
        try {
            System.out.println("X: " + x + " Y: " + y + " Z: " + z + " i: " + (z + World.CHUNK_SIZE * (y + World.CHUNK_SIZE * x)));
            return blocks[(x * World.CHUNK_SIZE * World.CHUNK_SIZE) + (y * World.CHUNK_SIZE) + z];
        } catch (Exception e) {
            return Block.AIR.getId();
        }
    }

}
