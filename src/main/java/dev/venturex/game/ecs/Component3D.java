package dev.venturex.game.ecs;

import dev.venturex.game.scenes.Scene;
import org.joml.Vector3f;

public abstract class Component3D extends Component {

    private Vector3f position;
    private Vector3f rotation;
    private float scale;

    public Component3D(String name, Scene scene) {
        super(name, scene);
        this.position = new Vector3f();
        this.rotation = new Vector3f();
        this.scale = 1;
    }

    public void move(Vector3f offset) {
        if (offset.z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * offset.z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * offset.z;
        }
        if (offset.x != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1f * offset.x;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * offset.x;
        }
    }

    public void rotate(Vector3f rotation) {
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void input() {

    }

    @Override
    public void render() {

    }

    @Override
    public void destroy() {

    }
}
