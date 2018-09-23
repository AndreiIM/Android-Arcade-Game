package com.andrei.bumblebee.screen;

import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.drawables.Bee;
import com.andrei.bumblebee.drawables.Hexagon;
import com.andrei.bumblebee.drawables.HoneyComb;
import com.andrei.bumblebee.drawables.SplashScene;
import com.andrei.bumblebee.pojos.Node;
import com.andrei.bumblebee.pojos.Point;
import com.andrei.bumblebee.utils.AppConstants;
import com.andrei.bumblebee.utils.AppUtilities;

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
