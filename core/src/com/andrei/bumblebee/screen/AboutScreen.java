package com.andrei.bumblebee.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.drawables.AboutScene;
import com.andrei.bumblebee.drawables.OptionsScene;

/** Intermediate screen which only loads out objects from the game*/
public class AboutScreen extends AbstractScreen implements InputProcessor {

    private AboutScene img = new AboutScene();

    InputMultiplexer im;

    public AboutScreen(BumblebeeGame game) {
        super(game);

        img.create();

        im = new InputMultiplexer();
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        System.out.println("AboutScreen show");

        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {
        img.render();
    }

    @Override
    public void dispose() {
        img.dispose();
    }

    @Override
    public void hide() {
        System.out.println("AboutScreen hide");
        //dispose();
        Gdx.input.setInputProcessor(null);  //cancel any input
    }

    @Override
    public boolean keyDown(int keycode) {
        //check for any key press
        if ((keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE)) {

            game.setScreen(game.getMenuScreen());
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
