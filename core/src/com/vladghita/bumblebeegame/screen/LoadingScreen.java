package com.vladghita.bumblebeegame.screen;

import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.drawables.Bee;
import com.vladghita.bumblebeegame.drawables.Hexagon;
import com.vladghita.bumblebeegame.drawables.HoneyComb;
import com.vladghita.bumblebeegame.drawables.SplashScene;
import com.vladghita.bumblebeegame.pojos.Node;
import com.vladghita.bumblebeegame.pojos.Point;
import com.vladghita.bumblebeegame.utils.AppConstants;
import com.vladghita.bumblebeegame.utils.AppUtilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** Intermediate screen which only loads out objects from the game*/
public class LoadingScreen extends AbstractScreen {

    private SplashScene img = new SplashScene();

    public LoadingScreen(BumblebeeGame game) {
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

        doLoad();

        //set next screen
        game.setScreen(game.getMenuScreen());
}

    @Override
    public void dispose() {
        img.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

    public void doLoad(){
        //create all objects
        game.getBackgroundScene().create();
        game.getHoneyComb().createWithDefaults();
        game.getBumbleBee().create();
        game.getScoreBoard().create();
        game.getStarsEffect().create();
        game.getDialogBoard().create();
    }
}
