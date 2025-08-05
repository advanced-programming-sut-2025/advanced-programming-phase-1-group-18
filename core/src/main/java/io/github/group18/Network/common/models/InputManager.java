package io.github.group18.Network.common.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

public class InputManager {

    private static InputMultiplexer multiplexer;

    public static void setupInputProcessor()
    {
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
    }

    public static InputMultiplexer getMultiplexer() {
        return multiplexer;
    }
}
