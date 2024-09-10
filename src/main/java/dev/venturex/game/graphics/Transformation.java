package dev.venturex.game.graphics;

import dev.venturex.game.ecs.Component3D;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f projectionMatrix;
    private final Matrix4f modelViewMatrix;
    private final Matrix4f viewMatrix;

    public Transformation() {
        modelViewMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    public Matrix4f getViewMatrix(Camera3D camera) {
        Vector3f camPos = camera.getPosition();
        Vector3f camRot = camera.getRotation();

        viewMatrix.identity();
        viewMatrix
                .rotate((float) Math.toRadians(camRot.x), new Vector3f(1,0,0))
                .rotate((float) Math.toRadians(camRot.y), new Vector3f(0,1,0));
        viewMatrix.translate(-camPos.x, -camPos.y, -camPos.z);
        return viewMatrix;
    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        float aspectRatio = width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
        return projectionMatrix;
    }

    public Matrix4f getModelViewMatrix(Component3D component, Matrix4f viewMatrix) {
        Vector3f rotation = component.getRotation();
        modelViewMatrix.identity()
                .translate(component.getPosition())
                .rotateX((float) Math.toRadians(-rotation.x))
                .rotateY((float) Math.toRadians(-rotation.y))
                .rotateZ((float) Math.toRadians(-rotation.z))
                .scale(component.getScale());
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }
}
