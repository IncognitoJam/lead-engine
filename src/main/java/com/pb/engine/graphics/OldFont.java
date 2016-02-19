package com.pb.engine.graphics;

import com.pb.engine.utils.ResourceManager;

public class OldFont {

    private Texture texture;

    public Texture loadFont(String name, String location) {
        this.texture = Texture.loadTexture(location);
        ResourceManager.addTexture(name, this.texture);
        return this.texture;
    }

    public void bind() {
        this.texture.bind();
    }

    public void unbind() {
        this.texture.unbind();
    }

}