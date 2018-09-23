package com.vladghita.bumblebeegame.screen;

import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.drawables.SplashScene;

/** First screen shown at start of the game*/
public class SplashScreen extends AbstractScreen {

    private SplashScene img = new SplashScene();

    public SplashScreen(BumblebeeGame game) {
        super(game);

        img.create();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render(float delta) {
        img.render();

        game.setScreen(new LoadingScreen(game));
    }

    @Override
    public void dispose() {
        img.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }
}
