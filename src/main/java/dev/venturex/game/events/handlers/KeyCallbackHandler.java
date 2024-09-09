package dev.venturex.game.events.handlers;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyCallbackHandler {

    private static KeyCallbackHandler instance;
    private boolean keyPressed[] = new boolean[350];
    private boolean keyDown[] = new boolean[350];

    private KeyCallbackHandler(){}

    public static KeyCallbackHandler get() {
        if (KeyCallbackHandler.instance == null)
            KeyCallbackHandler.instance = new KeyCallbackHandler();
        return KeyCallbackHandler.instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
}
