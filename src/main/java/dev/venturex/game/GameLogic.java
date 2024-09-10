package dev.venturex.game;

import dev.venturex.game.events.Event;
import dev.venturex.game.scenes.Scene;
import static org.lwjgl.opengl.GL11.*;

public class GameLogic implements IGameLogic {

    public static GameConfiguration gameConfig;

    private Window window;
    private Scene gameScene;

    @Override
    public void run(Scene scene) {
        this.gameScene = scene;
        init();
        update();
        cleanUp();
    }

    @Override
    public void init() {

        window = new Window();
        window.open();

        gameScene.init();
    }

    @Override
    public void update() {
        while (!window.shouldClose()) {
            glClearColor(0f, 0.5f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            gameScene.update();
            gameScene.render();

            window.swapBuffers();
            window.pollEvents();
        }
    }

    @Override
    public void event(Event event) {

    }

    @Override
    public void render() {

    }

    @Override
    public void cleanUp() {
        gameScene.destroy();
        window.destroy();
    }
}
