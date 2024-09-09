package dev.venturex.game;

import dev.venturex.game.events.handlers.FramebufferCallbackHandler;
import dev.venturex.game.events.handlers.KeyCallbackHandler;
import dev.venturex.game.events.handlers.MouseCallbackHandler;
import dev.venturex.game.graphics.Graphics;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;
import org.tinylog.Logger;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private long glfwWindowHandle;

    private int width, height;

    public void open() {
        glfwWindowHandle = Graphics.createGraphicsProfile();
        glfwMakeContextCurrent(glfwWindowHandle);

        glfwSetKeyCallback(glfwWindowHandle, KeyCallbackHandler::keyCallback);
        glfwSetMouseButtonCallback(glfwWindowHandle, MouseCallbackHandler::mouseButtonCallback);
        glfwSetCursorPosCallback(glfwWindowHandle, MouseCallbackHandler::mousePosCallback);

        glfwSetFramebufferSizeCallback(glfwWindowHandle, this::framebufferSizeCallback);

        glfwSetErrorCallback((int errorCode, long msgPtr) ->
                Logger.error("Error code [{}], msg [{}]", errorCode, MemoryUtil.memUTF8(msgPtr)));

        Graphics.showWindow(glfwWindowHandle);

        int[] arrWidth = new int[1];
        int[] arrHeight = new int[1];
        glfwGetFramebufferSize(glfwWindowHandle, arrWidth, arrHeight);
        width = arrWidth[0];
        height = arrHeight[0];

        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(glfwWindowHandle);
    }

    public void framebufferSizeCallback(long windowId, int width, int height) {
        glViewport(0, 0, width, height);
        this.width = width;
        this.height = height;
    }

    public void destroy() {
        glfwFreeCallbacks(glfwWindowHandle);
        glfwDestroyWindow(glfwWindowHandle);
        glfwTerminate();
        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null)
            callback.free();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(glfwWindowHandle);
    }

    public long getGlfwWindowHandle() {
        return glfwWindowHandle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
