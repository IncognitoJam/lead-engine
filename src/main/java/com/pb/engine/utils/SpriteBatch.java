/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL15
 *  org.lwjgl.opengl.GL20
 */
package com.pb.engine.utils;

import java.nio.FloatBuffer;

import com.pb.engine.graphics.Texture;
import com.pb.engine.graphics.shader.Shader;
import com.pb.engine.graphics.shader.ShaderProgram;
import com.pb.engine.maths.Color4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class SpriteBatch {

    public static int TYPE_DYNAMIC = 0;
    public static int TYPE_STATIC = 1;
    private FloatBuffer vertices;
    private FloatBuffer colorVertices;
    private FloatBuffer textureVertices;
    private ShaderProgram currentShader;
    private Texture currentTexture;
    private Texture defaultTexture = Texture.createEmptyTexture();
    private Color4f currentColor;
    private Color4f defaultColor = Color4f.DEFAULT;
    private int type;
    private int size;
    private int currentSize;
    private int vID;
    private int cID;
    private int tID;
    private int shaderProgram;
    private boolean render2D;
    private boolean active;

    public SpriteBatch() {
        this(TYPE_STATIC, 100000, true);
    }

    public SpriteBatch(int type, int size, boolean render2D) {
        this.type = type;
        this.size = size;
        this.render2D = render2D;
        this.active = false;
        this.currentSize = 0;
        this.createBuffers();
    }

    private void createBuffers() {
        if (this.render2D) {
            this.vertices = BufferUtils.createFloatBuffer(2 * this.size);
        } else if (!this.render2D) {
            this.vertices = BufferUtils.createFloatBuffer(3 * this.size);
        }
        this.colorVertices = BufferUtils.createFloatBuffer(4 * this.size);
        this.textureVertices = BufferUtils.createFloatBuffer(2 * this.size);
        if (this.type == TYPE_STATIC) {
            this.vID = GL15.glGenBuffers();
            GL15.glBindBuffer(34962, this.vID);
            GL15.glBufferData(34962, this.vertices, 35044);
            GL15.glBindBuffer(34962, 0);
            this.cID = GL15.glGenBuffers();
            GL15.glBindBuffer(34962, this.cID);
            GL15.glBufferData(34962, this.colorVertices, 35044);
            GL15.glBindBuffer(34962, 0);
            this.tID = GL15.glGenBuffers();
            GL15.glBindBuffer(34962, this.cID);
            GL15.glBufferData(34962, this.textureVertices, 35044);
            GL15.glBindBuffer(34962, 0);
        }
    }

    public void begin() {
        if (this.active) {
            throw new IllegalStateException("Must call end() before begin()!");
        }
        this.active = true;
    }

    public void render() {
        GL11.glEnable(3553);
        GL11.glEnableClientState(32884);
        GL11.glEnableClientState(32886);
        GL11.glEnableClientState(32888);
        if (this.type == TYPE_STATIC) {
            this.renderStatic();
        }
        if (this.type == TYPE_DYNAMIC) {
            this.renderDynamic();
        }
        GL11.glDrawArrays(4, 0, this.vertices.remaining() + this.colorVertices.remaining() + this.textureVertices.remaining());
        GL11.glDisableClientState(32884);
        GL11.glDisableClientState(32886);
        GL11.glDisableClientState(32888);
    }

    private void renderStatic() {
        if (this.render2D) {
            GL15.glBindBuffer(34962, this.vID);
            GL11.glVertexPointer(2, 5126, 0, (long)0);
        } else if (!this.render2D) {
            GL15.glBindBuffer(34962, this.vID);
            GL11.glVertexPointer(3, 5126, 0, (long)0);
        }
        GL15.glBindBuffer(34962, this.cID);
        GL11.glColorPointer(4, 5126, 0, (long)0);
        GL15.glBindBuffer(34962, this.tID);
        GL11.glTexCoordPointer(2, 5126, 0, (long)0);
    }

    private void renderDynamic() {
        GL15.glBindBuffer(34962, 0);
        if (this.render2D) {
            GL11.glVertexPointer(2, 0, this.vertices);
        } else if (!this.render2D) {
            GL11.glVertexPointer(3, 0, this.vertices);
        }
        GL11.glColorPointer(4, 0, this.colorVertices);
        GL11.glTexCoordPointer(2, 0, this.textureVertices);
    }

    public void end() {
        if (!this.active) {
            throw new IllegalStateException("Must call begin() before end()!");
        }
        this.vertices.flip();
        this.colorVertices.flip();
        this.textureVertices.flip();
        this.render();
        this.vertices.clear();
        this.colorVertices.clear();
        this.textureVertices.clear();
        this.active = false;
    }

    public void useShader() {
        if (this.currentShader != null) {
            GL20.glUseProgram(this.shaderProgram);
        }
    }

    public void useShader(ShaderProgram program) {
        program.use();
    }

    public void useShader(ResourceManager rm, String name) {
        ResourceManager.loadShaderProgram(name).use();
    }

    public void releaseShader() {
        GL20.glUseProgram(0);
    }

    public void addShader(String vLoc, String fLoc) {
        Shader temp = new Shader(vLoc, fLoc);
        ShaderProgram tempProgram = new ShaderProgram(temp.getvShader(), temp.getfShader());
        if (this.currentShader != null) {
            this.currentShader.release();
            this.currentShader.cleanup();
        }
        this.currentShader = tempProgram;
    }

    public void addShader(ShaderProgram shader) {
        if (this.currentShader != null) {
            this.currentShader.release();
            this.currentShader.cleanup();
        }
        this.currentShader = shader;
    }

    public void addShader(ResourceManager rm, String name) {
        if (this.currentShader != null) {
            this.currentShader.release();
            this.currentShader.cleanup();
        }
        this.currentShader = ResourceManager.loadShaderProgram(name);
    }

    public void putData(float x, float y) {
        this.putData(x, y, 0.0f, this.defaultColor.r, this.defaultColor.g, this.defaultColor.b, this.defaultColor.a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, float z) {
        this.putData(x, y, z, this.defaultColor.r, this.defaultColor.g, this.defaultColor.b, this.defaultColor.a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, float r, float g, float b, float a) {
        this.putData(x, y, 0.0f, r, g, b, a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, float z, float r, float g, float b, float a) {
        this.putData(x, y, z, r, g, b, a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, Color4f color) {
        this.putData(x, y, 0.0f, color.r, color.g, color.b, color.a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, float z, Color4f color) {
        this.putData(x, y, z, color.r, color.g, color.b, color.a, 0.0f, 0.0f);
    }

    public void putData(float x, float y, Color4f color, float u, float v) {
        this.putData(x, y, 0.0f, color.r, color.g, color.b, color.a, u, v);
    }

    public void putData(float x, float y, float z, Color4f color, float u, float v) {
        this.putData(x, y, z, color.r, color.g, color.b, color.a, u, v);
    }

    public void putData(float x, float y, float z, float r, float g, float b, float a, float u, float v) {
        if (z != 0.0f) {
            this.putData(new VertexData(x, y, z, r, g, b, a, u, v));
        } else if (z == 0.0f) {
            this.putData(new VertexData(x, y, r, g, b, a, u, v));
        }
    }

    public void putData(VertexData data) {
        if (this.currentSize >= this.size - 1) {
            if (this.render2D) {
                this.vertices.put(data.x).put(data.y);
            } else if (!this.render2D) {
                this.vertices.put(data.x).put(data.y).put(data.z);
            }
        }
        this.colorVertices.put(data.r).put(data.g).put(data.b).put(data.a);
        this.textureVertices.put(data.u).put(data.v);
        ++this.currentSize;
    }

    private void restartBatch() {
        this.end();
        this.begin();
    }

    public void dispose() {
        GL15.glDeleteBuffers(this.vID);
        GL15.glDeleteBuffers(this.cID);
        GL15.glDeleteBuffers(this.tID);
    }

    private static class VertexData {
        public float x;
        public float y;
        public float z;
        public float r;
        public float g;
        public float b;
        public float a;
        public float u;
        public float v;

        public VertexData(float x, float y, float z, float r, float g, float b, float a, float u, float v) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
            this.u = u;
            this.v = v;
        }

        public VertexData(float x, float y, float r, float g, float b, float a, float u, float v) {
            this.x = x;
            this.y = y;
            this.z = 0.0f;
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
            this.u = u;
            this.v = v;
        }
    }

}

