package com.pb.engine.maths;

public class Vector2i {
    private int x;
    private int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i add(Vector2i r) {
        return new Vector2i(this.x + r.getX(), this.y + r.getY());
    }

    public Vector2i add(int r) {
        return new Vector2i(this.x + r, this.y + r);
    }

    public Vector2i sub(Vector2i r) {
        return new Vector2i(this.x - r.getX(), this.y - r.getY());
    }

    public Vector2i sub(int r) {
        return new Vector2i(this.x - r, this.y - r);
    }

    public Vector2i mul(Vector2i r) {
        return new Vector2i(this.x * r.getX(), this.y * r.getY());
    }

    public Vector2i mul(int r) {
        return new Vector2i(this.x * r, this.y * r);
    }

    public Vector2i div(Vector2i r) {
        return new Vector2i(this.x / r.getX(), this.y / r.getY());
    }

    public Vector2i div(int r) {
        return new Vector2i(this.x / r, this.y / r);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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

    public String toString() {
        return "(" + this.x + " , " + this.y + ")";
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Vector2i && ((Vector2i) object).getX() == this.getX() && ((Vector2i) object).getY() == this.getY();
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

}

