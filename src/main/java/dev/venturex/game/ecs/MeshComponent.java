package dev.venturex.game.ecs;

import dev.venturex.game.graphics.Mesh;
import dev.venturex.game.graphics.ShaderProgram;
import dev.venturex.game.graphics.Texture;
import dev.venturex.game.graphics.Transformation;
import dev.venturex.game.scenes.Scene;

import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class MeshComponent extends Component3D {

    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private final float[] vertices = new float[] {
            // positions            // colors                   // tex-coords
            -0.5f,  0.5f, 0.5f,     0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.0f,
            -0.5f, -0.5f, 0.5f,     0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.5f,
             0.5f, -0.5f, 0.5f,     0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.5f,
             0.5f,  0.5f, 0.5f,     0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.0f,

            -0.5f,  0.5f, -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.0f,
             0.5f,  0.5f, -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.0f,
            -0.5f, -0.5f, -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.5f,
             0.5f, -0.5f, -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.5f,

            -0.5f, 0.5f,  -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.5f,
            0.5f, 0.5f,   -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.5f,
            -0.5f, 0.5f,   0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 1.0f,
            0.5f, 0.5f,    0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 1.0f,
            0.5f, 0.5f,    0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.0f,
            0.5f, -0.5f,   0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.0f, 0.5f,
            -0.5f, 0.5f,   0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.0f,
            -0.5f, -0.5f,  0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.0f,
            0.5f, -0.5f,  -0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     0.5f, 0.5f,
            0.5f, -0.5f,   0.5f,    0.0f, 0.0f, 0.0f, 1.0f,     1.0f, 0.5f,
    };

    private final int[] indices = new int[] {
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,
    };

    private ShaderProgram shaderProgram;
    private Mesh mesh;
    private final Transformation transformation;
    private Texture texture;

    public MeshComponent(Scene scene) {
        super("MeshComponent", scene);
        transformation = new Transformation();
    }

    @Override
    public void init() {
        this.shaderProgram = new ShaderProgram("src/main/resources/triangle.vert", "src/main/resources/triangle.frag");
        shaderProgram.link();
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");
        texture = new Texture("src/main/resources/textures/cube_texture.png");

        mesh = new Mesh(vertices, indices);
    }

    @Override
    public void update() {

    }

    @Override
    public void input() {

    }

    @Override
    public void render() {
        shaderProgram.bind();

        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, 1280, 720, Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        Matrix4f viewMatrix = transformation.getViewMatrix(parentScene.defaultCamera);

        Matrix4f modelViewMatrix = transformation.getModelViewMatrix(this, viewMatrix);
        shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);

        shaderProgram.setUniform("texture_sampler", 0);

        mesh.render();

        glActiveTexture(GL_TEXTURE0);
        texture.bind();

        shaderProgram.unbind();
    }

    @Override
    public void destroy() {
        mesh.destroy();
        shaderProgram.cleanup();
    }
}
