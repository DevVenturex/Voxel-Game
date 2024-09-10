package dev.venturex.game;

import dev.venturex.game.scenes.TestScene;
import org.tinylog.Logger;

public class EntryGame {

    public static void main(String[] args) {
        IGameLogic gameLogic = new GameLogic();
        gameLogic.run(new TestScene());
    }
}
