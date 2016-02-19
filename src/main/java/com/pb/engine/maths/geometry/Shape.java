package com.pb.engine.maths.geometry;

import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.maths.Color4f;
import com.pb.engine.world.blocks.Block;

import static org.lwjgl.opengl.GL11.*;

public class Shape {

    public static void createCube(float x, float y, float z, byte id, float size) {
        if (id == 0)
            return;

        Color4f color = Block.getBlock(id).getColor();
        float[] texCoords = Block.getTexCoords(id);

        // Bottom Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x, y, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x + size, y, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y, z);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y, z);


        // Top Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x, y + size, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x + size, y + size, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y + size, z + size);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y + size, z + size);


        // Front Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x, y, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x + size, y, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y + size, z);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y + size, z);


        // Back Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x, y + size, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x + size, y + size, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y, z + size);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y, z + size);


        // Left Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x + size, y, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x + size, y, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y + size, z + size);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x + size, y + size, z);

        // Right Face
        glColor4f(color.r, color.g, color.b, color.a);
        glTexCoord2f(texCoords[0], texCoords[1]);
        glVertex3f(x, y, z + size);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1]);
        glVertex3f(x, y, z);
        glTexCoord2f(texCoords[0] + Spritesheet.blocks.uniformSize(), texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y + size, z);
        glTexCoord2f(texCoords[0], texCoords[1] + Spritesheet.blocks.uniformSize());
        glVertex3f(x, y + size, z + size);
    }

}
