package com.pb.engine.game;

import com.pb.engine.graphics.Camera;
import com.pb.engine.graphics.Camera3D;
import com.pb.engine.graphics.Font;
import com.pb.engine.graphics.Spritesheet;
import com.pb.engine.graphics.shader.Shader;
import com.pb.engine.graphics.shader.ShaderProgram;
import com.pb.engine.maths.Vector2f;
import com.pb.engine.maths.Vector3f;
import com.pb.engine.utils.Configuration;
import com.pb.engine.world.Chunk;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class BlockGame implements Game {

    public static BlockGame BLOCK;
    private Configuration gameConfig;

    private Camera camera;
    private ShaderProgram shader;
    private Font font;

    private Chunk chunk;

    private boolean renderDebug = true;

    @Override
    public void config(Configuration gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public void init() {
        BLOCK = this;

        camera = new Camera3D.CameraBuilder().setAspectRatio(Display.getWidth() / Display.getHeight())
                .setRotation(0, 0, 0)
                .setPosition(0, 0, 0)
                .setFieldOfView(67.0f)
                .build();
        font = new Font("fonts/arial-large.png");

        Spritesheet.blocks.bind();
        Shader temp = new Shader("/shaders/chunk.vert", "/shaders/chunk.frag");
        shader = new ShaderProgram(temp.getvShader(), temp.getfShader());

        chunk = new Chunk(shader, new Vector2f(0, 0));
    }

    @Override
    public void initGL() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_BLEND_SRC, GL_ONE_MINUS_SRC_ALPHA);

        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);

        glEnable(GL_CULL_FACE);
    }

    @Override
    public void update() {
        //world.update();
        chunk.update();

        input();
    }

    private void input() {
        camera.updateKeys(32, 2);
        camera.updateMouse(1, 90, -90);

        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        }

        while (Keyboard.next()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                cleanup();
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
                renderDebug = !renderDebug;
            }
        }
    }

    @Override
    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0f, .25f, .9f, 1f);

        render3D();
        camera.applyTranslations();

        chunk.render();

        if (renderDebug) {
            render2D();
            glColor3f(1f, 1f, 1f);
            renderDebug();
        }
    }

    private void render2D() {
        glCullFace(GL_BACK);
        glClearDepth(1);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        glOrtho(0, 1, 0, 1, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glDisable(GL_DEPTH_TEST);
    }

    private void render3D() {
        glCullFace(GL_FRONT);
        glViewport(0, 0, Display.getWidth(), Display.getHeight());
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        gluPerspective(60 * (float) Display.getWidth() / Display.getHeight(), (float) Display.getWidth() / Display.getHeight(), 0.1f, 100.0f);
        glMatrixMode(GL_MODELVIEW);

        glEnable(GL_DEPTH_TEST);
    }

    private void renderDebug() {
        font.renderString("FPS:" + GameLoop.getFPS(), 16, 0f, 0.5f - (0.0375f * 0.5f), 0.05f, 0.0375f);
        font.renderString("X:" + (int) camera.getX() + " Y:" + (int) camera.getY() + " " + "Z:" + (int) camera.getZ(), 16, 0f, 0.5f - (0.0375f * 0.9f), 0.05f, 0.0375f);
        font.renderString("RotX:" + (int) camera.getPitch() + " RotY:" + (int) camera.getYaw() + " " + "RotZ:" + (int) camera.getRoll(), 16, 0f, 0.5f - (0.0375f * 1.3f), 0.05f, 0.0375f);
    }

    @Override
    public void cleanup() {
        // world.save();
        shader.cleanup();
        chunk.cleanup();
        Spritesheet.blocks.cleanup();

        Display.destroy();
        System.exit(0);
    }

}
