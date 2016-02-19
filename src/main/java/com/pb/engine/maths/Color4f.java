/*
 * Decompiled with CFR 0_110.
 */
package com.pb.engine.maths;

public class Color4f {

    public static final Color4f BLACK = new Color4f(0.0f, 0.0f, 0.0f);
    public static final Color4f GRAY = new Color4f(0.5f, 0.5f, 0.5f);
    public static final Color4f WHITE = new Color4f(1.0f, 1.0f, 1.0f);

    public static final Color4f BLUE = new Color4f(0.0f, 0.0f, 1.0f);
    public static final Color4f RED = new Color4f(1.0f, 0.0f, 0.0f);
    public static final Color4f GREEN = new Color4f(0.0f, 1.0f, 0.0f);

    public static final Color4f TRANSPARENT = new Color4f(0.0f, 0.0f, 0.0f, 0.0f);

    public static final Color4f DEFAULT = WHITE;

    public float r, g, b, a;

    public Color4f() {
        this.r = 1f;
        this.g = 1f;
        this.b = 1f;
        this.a = 1f;
    }

    public Color4f(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1f;
    }

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color4f multiply(Color4f other) {
        return new Color4f(r * other.r, g * other.g, b * other.b, a * other.a);
    }

    public Color4f multiply(float constant) {
        return new Color4f(r * constant, g * constant, b * constant, a);
    }

    public Color4f getColor() {
        return this;
    }

    public boolean equals(Color4f other) {
        return r == other.r && g == other.g && b == other.b && a == other.a;
    }
}

