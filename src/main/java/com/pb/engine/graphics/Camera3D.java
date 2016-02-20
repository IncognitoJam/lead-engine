/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 */
package com.pb.engine.graphics;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera3D implements Camera {

    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;
    private float pitch = 0.0f;
    private float yaw = 0.0f;
    private float roll = 0.0f;
    private float aspectRatio = 1.0f;
    private float fov;
    public final float zNear;
    public final float zFar;

    public Camera3D() {
        this.zNear = 0.3f;
        this.zFar = 100.0f;
    }

    public Camera3D(float aspectRatio) {
        if (aspectRatio <= 0.0f) {
            throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
        }
        this.aspectRatio = aspectRatio;
        this.zNear = 0.3f;
        this.zFar = 100.0f;
    }

    public Camera3D(CameraBuilder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.pitch = builder.pitch;
        this.yaw = builder.yaw;
        this.roll = builder.roll;
        this.aspectRatio = builder.aspectRatio;
        this.zNear = builder.zNear;
        this.zFar = builder.zFar;
        this.fov = builder.fov;
    }

    public Camera3D(float aspectRatio, float x, float y, float z) {
        this(aspectRatio);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Camera3D(float aspectRatio, float x, float y, float z, float pitch, float yaw, float roll) {
        this(aspectRatio, x, y, z);
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    @Override
    public void updateMouse() {
        float MAX_UP = 90.0f;
        float MAX_DOWN = -90.0f;
        float mouseDX = (float) Mouse.getDX() * 0.16f;
        float mouseDY = (float) Mouse.getDY() * 0.16f;
        this.yaw = this.yaw + mouseDX >= 360.0f ? this.yaw + mouseDX - 360.0f : (this.yaw + mouseDX < 0.0f ? 360.0f - this.yaw + mouseDX : (this.yaw += mouseDX));
        if (this.pitch - mouseDY >= -90.0f && this.pitch - mouseDY <= 90.0f) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < -90.0f) {
            this.pitch = -90.0f;
        } else if (this.pitch - mouseDY > 90.0f) {
            this.pitch = 90.0f;
        }
    }

    @Override
    public void updateMouse(float mouseSpeed) {
        float MAX_UP = 90.0f;
        float MAX_DOWN = -90.0f;
        float mouseDX = (float) Mouse.getDX() * mouseSpeed * 0.16f;
        float mouseDY = (float) Mouse.getDY() * mouseSpeed * 0.16f;
        this.yaw = this.yaw + mouseDX >= 360.0f ? this.yaw + mouseDX - 360.0f : (this.yaw + mouseDX < 0.0f ? 360.0f - this.yaw + mouseDX : (this.yaw += mouseDX));
        if (this.pitch - mouseDY >= -90.0f && this.pitch - mouseDY <= 90.0f) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < -90.0f) {
            this.pitch = -90.0f;
        } else if (this.pitch - mouseDY > 90.0f) {
            this.pitch = 90.0f;
        }
    }

    @Override
    public void updateMouse(float mouseSpeed, float maxUp, float maxDown) {
        float mouseDX = (float) Mouse.getDX() * mouseSpeed * 0.16f;
        float mouseDY = (float) Mouse.getDY() * mouseSpeed * 0.16f;
        this.yaw = this.yaw + mouseDX >= 360.0f ? this.yaw + mouseDX - 360.0f : (this.yaw + mouseDX < 0.0f ? 360.0f - this.yaw + mouseDX : (this.yaw += mouseDX));
        if (this.pitch - mouseDY >= maxDown && this.pitch - mouseDY <= maxUp) {
            this.pitch += -mouseDY;
        } else if (this.pitch - mouseDY < maxDown) {
            this.pitch = maxDown;
        } else if (this.pitch - mouseDY > maxUp) {
            this.pitch = maxUp;
        }
    }

    @Override
    public void updateKeys(float delta) {
        if (delta <= 0.0f) {
            throw new IllegalArgumentException("Delta must be greater than 0");
        }
        boolean keyUp = Keyboard.isKeyDown(17);
        boolean keyDown = Keyboard.isKeyDown(31);
        boolean keyLeft = Keyboard.isKeyDown(30);
        boolean keyRight = Keyboard.isKeyDown(32);
        boolean space = Keyboard.isKeyDown(57);
        boolean shift = Keyboard.isKeyDown(42);
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            this.moveLookDir(delta * 0.003f, 0.0f, (-delta) * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            this.moveLookDir((-delta) * 0.003f, 0.0f, (-delta) * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            this.moveLookDir(0.0f, 0.0f, (-delta) * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            this.moveLookDir((-delta) * 0.003f, 0.0f, delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            this.moveLookDir(delta * 0.003f, 0.0f, delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            this.moveLookDir(0.0f, 0.0f, delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            this.moveLookDir((-delta) * 0.003f, 0.0f, 0.0f);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            this.moveLookDir(delta * 0.003f, 0.0f, 0.0f);
        }
        if (space && !shift) {
            this.y += delta * 0.003f;
        }
        if (shift && !space) {
            this.y -= delta * 0.003f;
        }
    }

    @Override
    public void updateKeys(float delta, float speed) {
        if (delta <= 0.0f) {
            throw new IllegalArgumentException("Delta must be greater than 0");
        }
        boolean keyUp = Keyboard.isKeyDown(17);
        boolean keyDown = Keyboard.isKeyDown(31);
        boolean keyLeft = Keyboard.isKeyDown(30);
        boolean keyRight = Keyboard.isKeyDown(32);
        boolean space = Keyboard.isKeyDown(57);
        boolean shift = Keyboard.isKeyDown(42);
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            this.moveLookDir(speed * delta * 0.003f, 0.0f, (-speed) * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            this.moveLookDir((-speed) * delta * 0.003f, 0.0f, (-speed) * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            this.moveLookDir(0.0f, 0.0f, (-speed) * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            this.moveLookDir((-speed) * delta * 0.003f, 0.0f, speed * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            this.moveLookDir(speed * delta * 0.003f, 0.0f, speed * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            this.moveLookDir(0.0f, 0.0f, speed * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            this.moveLookDir((-speed) * delta * 0.003f, 0.0f, 0.0f);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            this.moveLookDir(speed * delta * 0.003f, 0.0f, 0.0f);
        }
        if (space && !shift) {
            this.y += speed * delta * 0.003f;
        }
        if (shift && !space) {
            this.y -= speed * delta * 0.003f;
        }
    }

    @Override
    public void updateKeys(float delta, float speedX, float speedY, float speedZ) {
    }

    @Override
    public void moveLookDir(float dx, float dy, float dz) {
        this.z = (float) ((double) this.z + ((double) (dx * (float) Math.cos(Math.toRadians(this.yaw - 90.0f))) + (double) dz * Math.cos(Math.toRadians(this.yaw))));
        this.x = (float) ((double) this.x - ((double) (dx * (float) Math.sin(Math.toRadians(this.yaw - 90.0f))) + (double) dz * Math.sin(Math.toRadians(this.yaw))));
        this.y = (float) ((double) this.y + ((double) (dy * (float) Math.sin(Math.toRadians(this.pitch - 90.0f))) + (double) dz * Math.sin(Math.toRadians(this.pitch))));
    }

    @Override
    public void setPos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setFOV(float fov) {
        this.fov = fov;
    }

    @Override
    public void setAspectRation(float ratio) {
        this.aspectRatio = ratio;
    }

    @Override
    public void applyOrtho() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double) (-this.aspectRatio), (double) this.aspectRatio, -1.0, 1.0, 0.0, (double) this.zFar);
        GL11.glPopAttrib();
    }

    @Override
    public void applyProjection() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GLU.gluPerspective(this.fov, this.aspectRatio, this.zNear, this.zFar);
        GL11.glPopAttrib();
    }

    @Override
    public void applyTranslations() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5888);
        GL11.glRotatef(this.pitch, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(this.yaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.roll, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-this.x, -this.y, -this.z);
        GL11.glPopAttrib();
    }

    @Override
    public void setRotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getZ() {
        return this.z;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public float getYaw() {
        return this.yaw;
    }

    @Override
    public float getRoll() {
        return this.roll;
    }

    @Override
    public float getFOV() {
        return this.fov;
    }

    @Override
    public float getAspectRatio() {
        return this.aspectRatio;
    }

    @Override
    public float getNearClippingPlane() {
        return this.zNear;
    }

    @Override
    public float getFarClippingPlane() {
        return this.zFar;
    }

    public static class CameraBuilder {
        private float x = 0.0f;
        private float y = 0.0f;
        private float z = 0.0f;
        private float pitch = 0.0f;
        private float yaw = 0.0f;
        private float roll = 0.0f;
        private float aspectRatio = 1.0f;
        private float zNear = 0.001f;
        private float zFar = 100.0f;
        private float fov = 90.0f;

        public CameraBuilder setAspectRatio(float aspectRatio) {
            if (aspectRatio <= 0.0f) {
                throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
            }
            this.aspectRatio = aspectRatio;
            return this;
        }

        public CameraBuilder setNearClippingPane(float nearClippingPane) {
            if (nearClippingPane <= 0.0f) {
                throw new IllegalArgumentException("nearClippingPane " + nearClippingPane + " is 0 or less");
            }
            this.zNear = nearClippingPane;
            return this;
        }

        public CameraBuilder setFarClippingPane(float farClippingPane) {
            if (farClippingPane <= 0.0f) {
                throw new IllegalArgumentException("farClippingPane " + farClippingPane + " is 0 or less");
            }
            this.zFar = farClippingPane;
            return this;
        }

        public CameraBuilder setFieldOfView(float fov) {
            this.fov = fov;
            return this;
        }

        public CameraBuilder setPosition(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public CameraBuilder setRotation(float pitch, float yaw, float roll) {
            this.pitch = pitch;
            this.yaw = yaw;
            this.roll = roll;
            return this;
        }

        public Camera3D build() {
            if (this.zFar <= this.zNear) {
                throw new IllegalArgumentException("farClippingPane " + this.zFar + " is the same or less than " + "nearClippingPane " + this.zNear);
            }
            return new Camera3D(this);
        }
    }

}

