package com.pb.engine.graphics;

import com.pb.engine.maths.Color4f;

import static org.lwjgl.opengl.GL11.*;

public class Text {

    public static void renderString(OldFont font, String string, float x, float y, float scale, Color4f color) {
        Text.renderString(font, string, 16, x, y, 0.1f, 0.1f, scale, color);
    }

    public static void renderString(OldFont font, String string, int gridSize, float x, float y, float charWidth, float charHeight, float scale, Color4f color) {
        glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);

        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        font.bind();
        glEnable(GL_BLEND);
        glBlendFunc(1, 1);
        glColor4f(color.r, color.g, color.b, color.a);
        glPushMatrix();
        glScalef(scale, scale, 0.0f);
        glTranslatef(x, y, 0.0f);
        glBegin(GL_QUADS);

        int i = 0;
        while (i < string.length()) {
            char code = string.charAt(i);
            float cellSize = 1.0f / (float) gridSize;
            float cellX = (float) (code % gridSize) * cellSize;
            float cellY = (float) (code / gridSize) * cellSize;
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f((float) i * charWidth / 3.0f, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f((float) i * charWidth / 3.0f + charWidth / 2.0f, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f((float) i * charWidth / 3.0f + charWidth / 2.0f, y + charHeight);
            glTexCoord2f(cellX, cellY);
            glVertex2f((float) i * charWidth / 3.0f, y + charHeight);
            i++;
        }

        glEnd();
        glPopMatrix();
        font.unbind();
        glPopAttrib();
    }
}