package com.vladghita.bumblebeegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.drawables.Bee;
import com.vladghita.bumblebeegame.drawables.Hexagon;
import com.vladghita.bumblebeegame.drawables.HoneyComb;
import com.vladghita.bumblebeegame.pojos.Node;
import com.vladghita.bumblebeegame.pojos.Point;
import com.vladghita.bumblebeegame.utils.AppConstants;
import com.vladghita.bumblebeegame.utils.AppUtilities;
import com.vladghita.bumblebeegame.utils.BumblebeePreferences;
import com.vladghita.bumblebeegame.utils.Toast;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** Game screen */
public class GameScreen extends AbstractScreen implements InputProcessor {

    private Toast gameFailedToaster;
    private Toast gameWinToaster;
    private BumblebeePreferences pref;

    InputMultiplexer im;

    public GameScreen(BumblebeeGame game) {
        super(game);

        gameFailedToaster = new Toast(AppConstants.TOAST_MAX_TOASTS, AppConstants.TOAST_MARGIN);
        gameFailedToaster.makeText(game.getLangBundle().get(AppConstants.GAME_FAILED),
                AppConstants.FONT_JOKERMAN,
                AppConstants.TOAST_FONT_SCALE,
                Toast.COLOR_PREF.BLACK,
                Toast.STYLE.NORMAL,
                Toast.TEXT_POS.middle,
                Toast.TEXT_POS.middle_down,
                Toast.LONG);

        gameWinToaster = new Toast(AppConstants.TOAST_MAX_TOASTS, AppConstants.TOAST_MARGIN);
        gameWinToaster.makeText(game.getLangBundle().get(AppConstants.GAME_WIN),
                AppConstants.FONT_JOKERMAN,
                AppConstants.TOAST_FONT_SCALE,
                Toast.COLOR_PREF.BLACK,
                Toast.STYLE.NORMAL,
                Toast.TEXT_POS.middle,
                Toast.TEXT_POS.middle_down,
                Toast.LONG);

        game.getScoreBoard().setStrTotal(game.getLangBundle().get(AppConstants.SCORE_TOTAL));
        game.getScoreBoard().setStrSteps(game.getLangBundle().get(AppConstants.SCORE_STEPS));

        //bag in preferences
       // String playerName = game.getOptionsScreen().playerNameTextfield.getText();
        //pref.setPlayerName(playerName);
       // Boolean soundEnabled = game.getOptionsScreen().soundEnabledCheckBox.isChecked();
       // pref.setSoundEnabled(soundEnabled);
       // String levelChosed = game.getOptionsScreen().levelSelectBox.getSelected();
        //pref.setLevelChosed(levelChosed);

        im = new InputMultiplexer();
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBackgroundScene().render();
        game.getScoreBoard().render();
        game.getDialogBoard().render();
        game.getHoneyComb().render();
        game.getBumbleBee().render();

        for (Bee bee : game.getBeeList()) {
            bee.render();
        }

        if(game.getGameplayManager().isGameOver()){
            if(game.getGameplayManager().isGameWin()) {
                gameWinToaster.toaster();
            }
            else {
                gameFailedToaster.toaster();
            }
        }

        game.getStarsEffect().render();
    }



    @Override
    public void show() {
        System.out.println("GameScreen show");

        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        System.out.println("GameScreen hide");
        //dispose();
        Gdx.input.setInputProcessor(null);  //cancel any input
    }

    @Override
    public boolean keyDown(int keycode) {
        //check for any key press
        if ((keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE)) {
            if(!game.getGameplayManager().isGameOver())
                game.getMenuScreen().getNewGameButton().setButtonText(game.getLangBundle().get(AppConstants.MENU_CONTINUE));
            else
                game.getMenuScreen().getNewGameButton().setButtonText(game.getLangBundle().get(AppConstants.MENU_NEW_GAME));

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

        if(game.getGameplayManager().isGameOver())
            return false;

        Iterator it = game.getHoneyComb().hexMap.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry)it.next();
            Hexagon hex = (Hexagon) pair.getValue();
            //get the touched screen point
            //TODO Remove this hack !!!
            //get the screen to world coordinates by unprojecting the camera:
            ////Vector3 vect = backgroundScene.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            Point touch = AppUtilities.worldToScreen(screenX, screenY);

            if(AppUtilities.checkIfPointISInsidePolygon(hex.getPoints(), touch)){

                Node node = AppUtilities.getNodeFromMapByHex(HoneyComb.nodeMap, hex);

                if(node!= null && !node.isObstacle()){      //check if hex was not already set as obstacle
                    Bee bee = new Bee(game);
                    bee.create();

                    System.out.println(hex.getIndexX() + " " + hex.getIndexY());
                    bee.moveTo(AppUtilities.worldToScreen(hex.getCenter().x, hex.getCenter().y));

                    node.setObstacle(true);
                    game.getGameplayManager().setTotalScore(AppConstants.MAX_SCORE);
                    game.getBeeList().add(bee);
                  //  game.getGameplayManager().setStartMoveTime(Math.round(Gdx.graphics.getDeltaTime()));
                    //try to process next move, or set game over if there is no available move
                    if(game.getGameplayManager().processMove());

                    //compute score(1/sqrt(time) + 2/(moves^2))*1000
                    game.getGameplayManager().setTurnNumber(game.getGameplayManager().getTurnNumber() + 1);
                   // game.getGameplayManager().setEndMoveTime((Math.round(Gdx.graphics.getDeltaTime()) - game.getGameplayManager().getStartMoveTime()));
                    game.getGameplayManager().setTotalScore(game.getGameplayManager().getTotalScore() - Math.round(1/ Math.sqrt(2/(Math.pow(game.getGameplayManager().getTurnNumber(),2)*1000))));

                    game.getGameplayManager().setTotalGameTime((game.getGameplayManager().getTotalGameTime() + game.getGameplayManager().getEndMoveTime()));
                    game.getScoreBoard().setValues(game.getGameplayManager().getTurnNumber(), game.getGameplayManager().getTotalScore());

                    //also show effect
                    game.getStarsEffect().respawn(new Point(hex.getCenter().x, hex.getCenter().y));


                    break;
                }
            }

        }

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
