/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL20
 */
package com.pb.engine.graphics.shader;

import org.lwjgl.opengl.GL20;

public class ShaderProgram {
    private int program;
    private int vShader;
    private int fShader;

    public ShaderProgram(int vShader, int fShader) {
        this.vShader = vShader;
        this.fShader = fShader;
        this.createShaderProgram();
    }

    private void createShaderProgram() {
        this.program = GL20.glCreateProgram();
        GL20.glAttachShader(this.program, this.vShader);
        GL20.glAttachShader(this.program, this.fShader);
        GL20.glLinkProgram(this.program);
        GL20.glValidateProgram(this.program);
    }

    public void use() {
        GL20.glUseProgram(this.program);
    }

    public void release() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        GL20.glDeleteProgram(this.program);
    }

    public int getProgram() {
        return this.program;
    }
}

