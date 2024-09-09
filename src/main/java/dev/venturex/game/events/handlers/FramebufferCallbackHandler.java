package dev.venturex.game.events.handlers;

public class FramebufferCallbackHandler {

    private static FramebufferCallbackHandler instance;

    private int width, height;
    private int lastWidth, lastHeight;
    private boolean sizeChanged;

    private FramebufferCallbackHandler() {}

    public static FramebufferCallbackHandler get() {
        if (FramebufferCallbackHandler.instance == null)
            FramebufferCallbackHandler.instance = new FramebufferCallbackHandler();
        return FramebufferCallbackHandler.instance;
    }

    public static void framebufferSizeCallback(long windowHandle, int width, int height) {
        get().lastWidth = get().width;
        get().lastHeight = get().height;
        get().width = width;
        get().height = height;
    }

    public static boolean isSizeChanged() {
        return get().width != get().lastWidth || get().height != get().lastHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
