package dev.venturex.game.scenes;

import dev.venturex.game.events.Event;
import dev.venturex.game.graphics.Camera3D;

public abstract class Scene {

    public Camera3D defaultCamera = new Camera3D(this);

    public abstract void init();
    public abstract void update();
    public abstract void event(Event event);
    public abstract void render();
    public abstract void destroy();
}
