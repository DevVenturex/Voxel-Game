package dev.venturex.game.graphics;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private final int programId;
    private int vertexId, fragmentId;
    private final Map<String, Integer> uniforms;

    public ShaderProgram(String vertexPath, String fragmentPath) {
        uniforms = new HashMap<>();
        programId = glCreateProgram();
        if (programId == 0)
            throw new RuntimeException("Could not create Program ID");

        Shader vertexShader = new Shader();
        Shader fragmentShader = new Shader();

        vertexId = vertexShader.createFromFilepath(vertexPath, GL_VERTEX_SHADER);
        glAttachShader(programId, vertexId);

        fragmentId = fragmentShader.createFromFilepath(fragmentPath, GL_FRAGMENT_SHADER);
        glAttachShader(programId, fragmentId);
    }

    public void link() {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0)
            throw new RuntimeException("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));

        glDetachShader(programId, vertexId);
        glDetachShader(programId, fragmentId);

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0)
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        glDeleteProgram(programId);
    }

    public void createUniform(String uName) {
        int uniformLocation = glGetUniformLocation(programId, uName);
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not create uniform: " + uName);
        }
        uniforms.put(uName, uniformLocation);
    }

    public void setUniform(String uName, int value) {
        glUniform1i(uniforms.get(uName), value);
    }

    public void setUniform(String uName, Matrix4f uValue) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            uValue.get(fb);
            glUniformMatrix4fv(uniforms.get(uName), false, fb);
        }
    }

    class Shader {
        private int id;

        public int createFromFilepath(String filepath, int type) {
            String source = "";
            try {
                source = new String(Files.readAllBytes(Paths.get(filepath)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return createFromSource(source, type);
        }

        public int createFromSource(String source, int type) {
            id = glCreateShader(type);
            if (id == 0)
                throw new RuntimeException("Could not create Shader ID");

            glShaderSource(id, source);
            glCompileShader(id);
            if (glGetShaderi(id, GL_COMPILE_STATUS) == 0)
                throw new RuntimeException("Error compiling Shader code: " + glGetShaderInfoLog(id, 1024));
            return id;
        }
    }
}
