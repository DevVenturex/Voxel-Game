package dev.venturex.game.graphics;

import dev.venturex.game.ecs.Component3D;
import dev.venturex.game.events.handlers.KeyCallbackHandler;
import dev.venturex.game.scenes.Scene;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;

public class Camera3D extends Component3D {

    public Camera3D(Scene scene) {
        super("CameraComponent", scene);
    }

    @Override
    public void update() {
        super.update();

        if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_W)) {
            move(new Vector3f(0,0, -0.1f));
        } else if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_S)) {
            move(new Vector3f(0,0,0.1f));
        }

        if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_D)) {
            move(new Vector3f(0.1f, 0,0));
        } else if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_A)) {
            move(new Vector3f(-0.1f, 0, 0));
        }

        if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_SPACE)) {
            this.setPosition(new Vector3f(this.getPosition().x, this.getPosition().y + 0.1f, this.getPosition().z));
        } else if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            this.setPosition(new Vector3f(this.getPosition().x, this.getPosition().y - 0.1f, this.getPosition().z));
        }

        if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_LEFT)) {
            rotate(new Vector3f(0, -3.0f, 0));
        } else if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_RIGHT)) {
            rotate(new Vector3f(0, 3.0f, 0));
        }

        if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_UP)) {
            rotate(new Vector3f(-3f, 0, 0));
        } else if (KeyCallbackHandler.isKeyPressed(GLFW_KEY_DOWN)) {
            rotate(new Vector3f(3f, 0, 0));
        }
    }
}
