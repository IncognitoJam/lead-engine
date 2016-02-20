/*
 * Decompiled with CFR 0_110.
 */
package com.pb.engine.maths;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public float dot(Vector3f r) {
        return this.x * r.getX() + this.y * r.getY() + this.z * r.getZ();
    }

    public Vector3f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        return this;
    }

    public Vector3f rotate() {
        return null;
    }

    public Vector3f add(Vector3f r) {
        return new Vector3f(this.x + r.getX(), this.y + r.getY(), this.z + r.getZ());
    }

    public Vector3f add(float r) {
        return new Vector3f(this.x + r, this.y + r, this.z + r);
    }

    public Vector3f sub(Vector3f r) {
        return new Vector3f(this.x - r.getX(), this.y - r.getY(), this.z - r.getZ());
    }

    public Vector3f sub(float r) {
        return new Vector3f(this.x - r, this.y - r, this.z - r);
    }

    public Vector3f mul(Vector3f r) {
        return new Vector3f(this.x * r.getX(), this.y * r.getY(), this.z * r.getZ());
    }

    public Vector3f mul(float r) {
        return new Vector3f(this.x * r, this.y * r, this.z * r);
    }

    public Vector3f div(Vector3f r) {
        return new Vector3f(this.x / r.getX(), this.y / r.getY(), this.z / r.getX());
    }

    public Vector3f div(float r) {
        return new Vector3f(this.x / r, this.y / r, this.z / r);
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

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        return (int) (x * 31 + y);
    }
}

