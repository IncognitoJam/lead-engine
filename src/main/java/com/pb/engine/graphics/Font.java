package com.pb.engine.graphics;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class Font {

    private Texture fontTexture;

    public Font(String location) {
        fontTexture = Texture.loadTexture(location);
    }

    /**
     * Renders text using the pre-loaded font bitmap.
     *
     * @param string          the string to render
     * @param gridSize        the dimensions of the bitmap grid (e.g. 16 -> 16x16 grid; 8 -> 8x8 grid)
     * @param x               the x-coordinate of the bottom-left corner of where the string starts rendering
     * @param y               the y-coordinate of the bottom-left corner of where the string starts rendering
     * @param characterWidth  the width of the character
     * @param characterHeight the height of the character
     */
    public void renderString(String string, int gridSize, float x, float y, float characterWidth, float characterHeight) {
        glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, fontTexture.getId());
        // Enable linear texture filtering for smoothed results.
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        // Enable additive blending. This means that the colours will be added to already existing colours in the
        // frame buffer. In practice, this makes the black parts of the texture become invisible.
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);
        // Store the current model-view matrix.
        glPushMatrix();
        // Offset all subsequent (at least up until 'glPopMatrix') vertex coordinates.
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        // Iterate over all the characters in the string.
        for (int i = 0; i < string.length(); i++) {
            // Get the ASCII-code of the character by type-casting to integer.
            int asciiCode = (int) string.charAt(i);
            // There are 16 cells in a texture, and a texture coordinate ranges from 0.0 to 1.0.
            final float cellSize = 1.0f / gridSize;
            // The cell's x-coordinate is the greatest integer smaller than remainder of the ASCII-code divided by the
            // amount of cells on the x-axis, times the cell size.
            float cellX = (asciiCode % gridSize) * cellSize;
            // The cell's y-coordinate is the greatest integer smaller than the ASCII-code divided by the amount of
            // cells on the y-axis.
            float cellY = (asciiCode / gridSize) * cellSize;
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * characterWidth / 3, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * characterWidth / 3 + characterWidth / 2, y + characterHeight);
            glTexCoord2f(cellX, cellY);
            glVertex2f(i * characterWidth / 3, y + characterHeight);
        }
        glEnd();
        glPopMatrix();
        glPopAttrib();
    }

    private void cleanup() {
        glDeleteTextures(fontTexture.getId());
        Display.destroy();
    }

}
