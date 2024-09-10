package dev.venturex.game.scenes;

import dev.venturex.game.ecs.MeshComponent;
import dev.venturex.game.events.Event;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class TestScene extends Scene {

    private List<MeshComponent> blocks;

    public TestScene() {
    }

    @Override
    public void init() {
        this.blocks = new ArrayList<>();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int z = 0; z < 4; z++) {
                    MeshComponent meshComponent = new MeshComponent(this);
                    meshComponent.init();
                    meshComponent.setPosition(new Vector3f(x, y, z));
                    blocks.add(meshComponent);
                }
            }
        }
    }

    Vector2f displayVec = new Vector2f();

    @Override
    public void update() {
        defaultCamera.update();

        for (MeshComponent block : blocks) {
            block.update();
        }
    }

    @Override
    public void event(Event event) {

    }

    @Override
    public void render() {
        for (MeshComponent block : blocks) {
            block.render();
        }
    }

    @Override
    public void destroy() {

    }
}
