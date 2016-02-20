package com.pb.engine.maths;

import com.pb.engine.world.World;

public class Vector3i {
    private int x;
    private int y;
    private int z;

    public static Vector3i blockCoordinate(int i) {
        int x = i / (World.CHUNK_SIZE * World.CHUNK_SIZE);
        int remainder = i - x * (World.CHUNK_SIZE * World.CHUNK_SIZE);
        int y = remainder / World.CHUNK_SIZE;
        remainder = remainder - y * World.CHUNK_SIZE;
        int z = remainder;
        return new Vector3i(x, y, z);
    }

    public static Vector3i chunkCoordinate(int i) {
        int x = i / (World.VIEW_DISTANCE * World.VIEW_DISTANCE);
        int remainder = i - x * (World.VIEW_DISTANCE * World.VIEW_DISTANCE);
        int y = remainder / World.VIEW_DISTANCE;
        remainder -= y * World.VIEW_DISTANCE;
        int z = remainder;
        return new Vector3i(x, y, z);
    }

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i add(Vector3i r) {
        return new Vector3i(this.x + r.getX(), this.y + r.getY(), this.z + r.getZ());
    }

    public Vector3i add(int r) {
        return new Vector3i(this.x + r, this.y + r, this.z + r);
    }

    public Vector3i sub(Vector3i r) {
        return new Vector3i(this.x - r.getX(), this.y - r.getY(), this.z - r.getZ());
    }

    public Vector3i sub(int r) {
        return new Vector3i(this.x - r, this.y - r, this.z - r);
    }

    public Vector3i mul(Vector3i r) {
        return new Vector3i(this.x * r.getX(), this.y * r.getY(), this.z * r.getZ());
    }

    public Vector3i mul(int r) {
        return new Vector3i(this.x * r, this.y * r, this.z * r);
    }

    public Vector3i div(Vector3i r) {
        return new Vector3i(this.x / r.getX(), this.y / r.getY(), this.z / r.getZ());
    }

    public Vector3i div(int r) {
        return new Vector3i(this.x / r, this.y / r, this.z / r);
    }

    public void setPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String toString() {
        return "(" + this.x + " , " + this.y + " , " + this.z + ")";
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Vector3i && ((Vector3i) object).getX() == this.getX() && ((Vector3i) object).getY() == this.getY() && ((Vector3i) object).getZ() == this.getZ();
    }

    @Override
    public int hashCode() {
        return x * 31 + y * 31 + z;
    }

}

