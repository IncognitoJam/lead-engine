package com.pb.engine.world;

import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.graphics.shader.ShaderProgram;
import com.pb.engine.maths.Vector2f;
import com.pb.engine.maths.Vector3f;
import com.pb.engine.maths.geometry.Shape;
import com.pb.engine.world.blocks.Block;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class Chunk {

    private ShaderProgram shader;

    private Vector2f pos;
    private byte[][][] blocks;

    private int vcID;

    public Chunk(ShaderProgram shader, Vector2f pos) {
        this.shader = shader;
        this.pos = pos;

        initGL();
        init();
    }

    public void initGL() {
        vcID = glGenLists(1);

        blocks = new byte[World.CHUNK_SIZE][World.CHUNK_SIZE][World.CHUNK_HEIGHT];

        createChunk();
        rebuild();
    }

    public void init() {

    }

    public void createChunk() {
        Random random = new Random();

        for (int x = 0; x < World.CHUNK_SIZE; x++) {
            for (int y = 0; y < World.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < World.CHUNK_SIZE; z++) {
                    float block = random.nextInt(99) / 10;
                    byte blockID;

                    if (block <= 1f)
                        blockID = Block.STONE.getId();
                    else if (block <= 2f)
                        blockID = Block.GRASS.getId();
                    else if (block <= 3f)
                        blockID = Block.DIRT.getId();
                    else if (block <= 4f)
                        blockID = Block.SAND.getId();
                    else
                        blockID = Block.AIR.getId();

                    blocks[x][y][z] = blockID;
                }
            }
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

        for (int x = 0; x < World.CHUNK_SIZE; x++) {
            for (int y = 0; y < World.CHUNK_SIZE; y++) {
                for (int z = 0; z < World.CHUNK_HEIGHT; z++) {
                    Shape.createCube(
                            x + (pos.getX() * World.CHUNK_SIZE),
                            y + (pos.getY() * World.CHUNK_SIZE),
                            y, blocks[x][y][z], 1f);
                }
            }
        }

        glEnd();
        glEndList();
    }

    public void cleanup() {

    }

    public Vector2f getPos() {
        return pos;
    }

    public byte[][][] getBlocks() {
        return blocks;
    }

    public byte getBlock(Vector3f position) {
        return getBlock((int) position.getX(), (int) position.getY(), (int) position.getZ());
    }

    public byte getBlock(int x, int y, int z) {
        return blocks[x][y][z];
    }

}
