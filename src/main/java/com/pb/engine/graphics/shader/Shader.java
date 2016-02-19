/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL20
 */
package com.pb.engine.graphics.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;

public class Shader {
    private String vertexLoc;
    private String fragmentLoc;
    private int vShader;
    private int fShader;

    public Shader(String vertexLoc, String fragmentLoc) {
        this.vertexLoc = vertexLoc;
        this.fragmentLoc = fragmentLoc;
        this.loadShader();
    }

    private void loadShader() {
        BufferedReader reader;
        String line;
        this.vShader = GL20.glCreateShader((int)35633);
        this.fShader = GL20.glCreateShader((int)35632);
        StringBuilder vSource = new StringBuilder();
        StringBuilder fSource = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(this.vertexLoc)));
            while ((line = reader.readLine()) != null) {
                vSource.append(line).append('\n');
            }
            reader.close();
        }
        catch (IOException e) {
            System.err.println(vSource);
            System.err.println("Error loading vertex shader source at Shader.java location: " + this.vertexLoc);
        }
        try {
            reader = new BufferedReader(new InputStreamReader(Shader.class.getResourceAsStream(this.fragmentLoc)));
            while ((line = reader.readLine()) != null) {
                fSource.append(line).append('\n');
            }
            reader.close();
        }
        catch (IOException e) {
            System.err.println(fSource);
            System.err.println("Error loading fragment shader source at Shader.java location: " + this.fragmentLoc);
        }
        this.compileShaders(vSource, fSource);
    }

    private void compileShaders(StringBuilder vSource, StringBuilder fSource) {
        GL20.glShaderSource((int)this.vShader, (CharSequence)vSource);
        GL20.glCompileShader((int)this.vShader);
        if (GL20.glGetShaderi((int)this.vShader, (int)35713) == 0) {
            System.err.println(GL20.glGetShaderInfoLog((int)this.vShader, (int)1024));
            System.err.println("Error compiling vertex shader at Shader.java location: " + this.vertexLoc);
        }
        GL20.glShaderSource((int)this.fShader, (CharSequence)fSource);
        GL20.glCompileShader((int)this.fShader);
        if (GL20.glGetShaderi((int)this.fShader, (int)35713) == 0) {
            System.err.println(GL20.glGetShaderInfoLog((int)this.fShader, (int)1024));
            System.err.println("Error compiling fragment shader at Shader.java location: " + this.fragmentLoc);
        }
    }

    public int getvShader() {
        return this.vShader;
    }

    public int getfShader() {
        return this.fShader;
    }

    public void dispose() {
        GL20.glDeleteShader((int)this.vShader);
        GL20.glDeleteShader((int)this.fShader);
    }
}

