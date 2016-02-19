/*
 * Decompiled with CFR 0_110.
 */
package com.pb.engine.maths;

public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float dot(Vector2f r) {
        return this.x * r.getX() + this.y * r.getY();
    }

    public Vector2f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        return this;
    }

    public Vector2f rotate(float angle) {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        return new Vector2f((float)((double)this.x * cos - (double)this.y * sin), (float)((double)this.x * sin + (double)this.y * cos));
    }

    public Vector2f add(Vector2f r) {
        return new Vector2f(this.x + r.getX(), this.y + r.getY());
    }

    public Vector2f add(float r) {
        return new Vector2f(this.x + r, this.y + r);
    }

    public Vector2f sub(Vector2f r) {
        return new Vector2f(this.x - r.getX(), this.y - r.getY());
    }

    public Vector2f sub(float r) {
        return new Vector2f(this.x - r, this.y - r);
    }

    public Vector2f mul(Vector2f r) {
        return new Vector2f(this.x * r.getX(), this.y * r.getY());
    }

    public Vector2f mul(float r) {
        return new Vector2f(this.x * r, this.y * r);
    }

    public Vector2f div(Vector2f r) {
        return new Vector2f(this.x / r.getX(), this.y / r.getY());
    }

    public Vector2f div(float r) {
        return new Vector2f(this.x / r, this.y / r);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + " , " + this.y + ")";
    }
}

