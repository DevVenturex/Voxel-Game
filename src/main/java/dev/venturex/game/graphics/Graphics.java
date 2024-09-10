package dev.venturex.game.graphics;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Graphics {

    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;
    public static String WINDOW_TITLE = "Voxel Game";

    public static boolean MAXIMIZED = false;
    public static boolean FULLSCREEN = false;
    public static boolean RESIZEABLE = true;
    public static boolean DECORATED = true;
    public static boolean VSYNC = true;

    // GLFW
    public static int CONTEXT_VERSION_MAJOR = 3;
    public static int CONTEXT_VERSION_MINOR = 2;

    // OpenGL
    public static boolean OPENGL_FORWARD_COMPAT = false;
    public static boolean OPENGL_DEBUG_CONTEXT = false;
    public static int OPENGL_PROFILE = GLFW_OPENGL_ANY_PROFILE;

    public static long createGraphicsProfile() {
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, CONTEXT_VERSION_MAJOR);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, CONTEXT_VERSION_MINOR);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, OPENGL_FORWARD_COMPAT ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, OPENGL_DEBUG_CONTEXT ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, OPENGL_PROFILE);
        glfwWindowHint(GLFW_MAXIMIZED, MAXIMIZED ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, RESIZEABLE ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, DECORATED ? GLFW_TRUE : GLFW_FALSE);

        long windowId = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE, NULL, NULL);
        if (windowId == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSwapInterval(VSYNC ? 1 : 0);
        return windowId;
    }

    public static void showWindow(long windowId) {
        glfwShowWindow(windowId);
    }
}
