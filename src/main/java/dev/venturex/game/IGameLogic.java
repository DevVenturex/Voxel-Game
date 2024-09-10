package dev.venturex.game;

import dev.venturex.game.events.Event;
import dev.venturex.game.scenes.Scene;

public interface IGameLogic {

    public static final GameConfiguration defaultConfig = new GameConfiguration();

    void run(Scene scene);
    void init();
    void update();
    void event(Event event);
    void render();
    void cleanUp();

    default void shotdown() {
        cleanUp();
    }
}
