package dev.venturex.game.events.handlers;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseCallbackHandler {

    private static MouseCallbackHandler instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseCallbackHandler() {
        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastY = 0;
        this.lastX = 0;
    }

    public static MouseCallbackHandler get() {
        if (MouseCallbackHandler.instance == null)
            MouseCallbackHandler.instance = new MouseCallbackHandler();
        return MouseCallbackHandler.instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xPos;
        get().yPos = yPos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().mouseButtonPressed.length)
                get().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getLastX() {
        return lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public static boolean isButtonPressed(int button) {
        return get().mouseButtonPressed[button];
    }
}
