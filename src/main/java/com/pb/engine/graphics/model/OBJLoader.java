/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Vector3f
 */
package com.pb.engine.graphics.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class OBJLoader {
    public static Model loadModel(File f) throws FileNotFoundException, IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model m = new Model();
        while ((line = reader.readLine()) != null) {
            float x;
            float z;
            float y;
            if (line.startsWith("v ")) {
                x = Float.valueOf(line.split(" ")[1]).floatValue();
                y = Float.valueOf(line.split(" ")[2]).floatValue();
                z = Float.valueOf(line.split(" ")[3]).floatValue();
                m.vertices.add(new Vector3f(x, y, z));
                continue;
            }
            if (line.startsWith("vn ")) {
                x = Float.valueOf(line.split(" ")[1]).floatValue();
                y = Float.valueOf(line.split(" ")[2]).floatValue();
                z = Float.valueOf(line.split(" ")[3]).floatValue();
                m.normals.add(new Vector3f(x, y, z));
                continue;
            }
            if (!line.startsWith("f ")) continue;
            Vector3f vertexIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[0]).floatValue(), Float.valueOf(line.split(" ")[2].split("/")[0]).floatValue(), Float.valueOf(line.split(" ")[3].split("/")[0]).floatValue());
            Vector3f normalIndices = new Vector3f(Float.valueOf(line.split(" ")[1].split("/")[2]).floatValue(), Float.valueOf(line.split(" ")[2].split("/")[2]).floatValue(), Float.valueOf(line.split(" ")[3].split("/")[2]).floatValue());
            m.faces.add(new ModelFace(vertexIndices, normalIndices));
        }
        reader.close();
        return m;
    }
}

