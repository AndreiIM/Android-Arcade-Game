package com.andrei.bumblebee.screen;

import com.badlogic.gdx.Screen;
import com.andrei.bumblebee.BumblebeeGame;

/** Abstract class representing a screen (view)*/
public abstract class AbstractScreen implements Screen {

    protected BumblebeeGame game;

    public AbstractScreen(BumblebeeGame game) {
        this.game = game;
    }

    @Override
    public abstract void pause();

    @Override
    public abstract void resume();

    @Override
    public abstract void dispose();
}
