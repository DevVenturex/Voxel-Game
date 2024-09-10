package dev.venturex.game.ecs;

import dev.venturex.game.scenes.Scene;

public abstract class Component {

    String name;
    Scene parentScene;

    public Component(String name, Scene scene) {
        this.name = name;
        this.parentScene = scene;
    }

    public abstract void init();
    public abstract void update();
    public abstract void input();
    public abstract void render();
    public abstract void destroy();
}
