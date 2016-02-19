package com.pb.engine.graphics;

public interface Camera {
    void updateMouse();

    void updateMouse(float mouseSpeed);

    void updateMouse(float mouseSpeed, float maxUp, float maxDown);

    void updateKeys(float var1);

    void updateKeys(float var1, float var2);

    void updateKeys(float var1, float var2, float var3, float var4);

    void moveLookDir(float var1, float var2, float var3);

    void setPos(float var1, float var2, float var3);

    void setFOV(float var1);

    void setAspectRation(float var1);

    void applyOrtho();

    void applyProjection();

    void applyTranslations();

    void setRotation(float var1, float var2, float var3);

    float getX();

    float getY();

    float getZ();

    float getPitch();

    float getYaw();

    float getRoll();

    float getFOV();

    float getAspectRatio();

    float getNearClippingPlane();

    float getFarClippingPlane();
}

