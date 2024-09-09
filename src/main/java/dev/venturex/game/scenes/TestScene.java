package dev.venturex.game.scenes;

import dev.venturex.game.ecs.MeshComponent;
import dev.venturex.game.events.Event;
import dev.venturex.game.events.handlers.FramebufferCallbackHandler;
import dev.venturex.game.events.handlers.KeyCallbackHandler;
import dev.venturex.game.events.handlers.MouseCallbackHandler;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class TestScene extends Scene {

    private MeshComponent cmp;

    public TestScene() {
    }

    @Override
    public void init() {
        cmp = new MeshComponent(this);
        cmp.init();

        cmp.setPosition(new Vector3f(0,0,-3));
    }

    Vector2f displayVec = new Vector2f();

    @Override
    public void update() {
        cmp.update();
        defaultCamera.update();
    }

    @Override
    public void event(Event event) {

    }

    @Override
    public void render() {
        cmp.render();
    }

    @Override
    public void destroy() {

    }
}
