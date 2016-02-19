/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Vector3f
 */
package com.pb.engine.graphics.model;

import org.lwjgl.util.vector.Vector3f;

public class ModelFace {
    public Vector3f vertex = new Vector3f();
    public Vector3f normal = new Vector3f();

    public ModelFace(Vector3f vertex, Vector3f normal) {
        this.vertex = vertex;
        this.normal = normal;
    }
}

