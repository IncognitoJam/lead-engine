/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.DisplayMode
 */
package com.pb.engine.graphics.window;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

    public static void createWindow(int width, int height, boolean mouseCaptured) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Mouse.setGrabbed(mouseCaptured);
            Display.create();
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void createWindow(int width, int height, String title, boolean mouseCaptured) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title);
            Mouse.setGrabbed(mouseCaptured);
            Display.create();
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void createWindow(int width, int height, String title, boolean mouseCaptured, boolean fullScreen) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setFullscreen(fullScreen);
            Display.setTitle(title);
            Mouse.setGrabbed(mouseCaptured);
            Display.create();
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        Display.update();
    }

    public static void update(int targetFPS) {
        Display.update();
        Display.sync(targetFPS);
    }

    public static boolean isWindowCloseRequested() {
        return Display.isCloseRequested();
    }

    public static void dispose() {
        Display.destroy();
    }

}

