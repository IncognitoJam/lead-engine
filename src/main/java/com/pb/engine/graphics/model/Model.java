package com.pb.engine.graphics.model;

import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public List<Vector3f> vertices = new ArrayList<Vector3f>();
    public List<Vector3f> normals = new ArrayList<Vector3f>();
    public List<ModelFace> faces = new ArrayList<ModelFace>();
}

