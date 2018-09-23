package com.bumblebee;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;
import com.andrei.bumblebee.drawables.BackgroundScene;
import com.andrei.bumblebee.drawables.Bee;
import com.andrei.bumblebee.drawables.Bumblebee;
import com.andrei.bumblebee.drawables.DialogBoard;
import com.andrei.bumblebee.drawables.Hexagon;
import com.andrei.bumblebee.drawables.HoneyComb;
import com.andrei.bumblebee.drawables.ScoreBoard;
import com.andrei.bumblebee.drawables.effects.StarsExplodeEffect;
import com.andrei.bumblebee.screen.AboutScreen;
import com.andrei.bumblebee.screen.GameScreen;
import com.andrei.bumblebee.screen.MenuScreen;
import com.andrei.bumblebee.screen.OptionsScreen;
import com.andrei.bumblebee.screen.SplashScreen;
import com.andrei.bumblebee.utils.AppConstants;
import com.andrei.bumblebee.utils.GameplayManager;
import com.andrei.bumblebee.utils.PathFinder;

import java.util.ArrayList;
import java.util.List;

public class BumblebeeGame extends Game {
    /**
     * Holds all our assets
     */
    //characters
    private BackgroundScene backgroundScene = new BackgroundScene();
    private Bumblebee bumbleBee = new Bumblebee();
    private HoneyComb honeyComb = new HoneyComb();
    private List<Bee> beeList = new ArrayList<Bee>();
    private PathFinder optimalPath = new PathFinder();
    private ScoreBoard scoreBoard = new ScoreBoard();
    private DialogBoard dialogBoard = new DialogBoard();
    private StarsExplodeEffect starsEffect = new StarsExplodeEffect();

    //game manager
    private GameplayManager gameplayManager = new GameplayManager(this);

    //screens
    private MenuScreen menuScreen = null;
    private GameScreen gameScreen = null;
    private OptionsScreen optionsScreen = null;
    private AboutScreen aboutScreen = null;

    public static int GAME_SCALE = 0;   //all the components of the game will be drawn relative to this
    public static Hexagon HONEYCOMB_CENTER = null;  //center of the honeycomb, where the bumblebee will be set

    I18NBundle langBundle = new I18NBundle();

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);   //catch the BACK key press in all screens

        //load internationalization file
        FileHandle baseFileHandle = Gdx.files.internal("data/" + AppConstants.BUNDLE_I18N_FILE);
        langBundle = I18NBundle.createBundle(baseFileHandle);

        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        backgroundScene.dispose();
        bumbleBee.dispose();
        honeyComb.dispose();

        if(menuScreen != null)
            menuScreen.dispose();

        if(gameScreen != null)
            gameScreen.dispose();

        if(optionsScreen != null)
            optionsScreen.dispose();

        if(aboutScreen != null)
            aboutScreen.dispose();

        for(Bee bee : beeList){
            bee.dispose();
        }

        scoreBoard.dispose();
        dialogBoard.dispose();

        starsEffect.dispose();
    }

    public BackgroundScene getBackgroundScene() {
        return backgroundScene;
    }

    public void setBackgroundScene(BackgroundScene backgroundScene) {
        this.backgroundScene = backgroundScene;
    }

    public Bumblebee getBumbleBee() {
        return bumbleBee;
    }

    public void setBumbleBee(Bumblebee bumbleBee) {
        this.bumbleBee = bumbleBee;
    }

    public HoneyComb getHoneyComb() {
        return honeyComb;
    }

    public void setHoneyComb(HoneyComb honeyComb) {
        this.honeyComb = honeyComb;
    }

    public List<Bee> getBeeList() {
        return beeList;
    }

    public void setBeeList(List<Bee> beeList) {
        this.beeList = beeList;
    }

    public GameplayManager getGameplayManager() {
        return gameplayManager;
    }

    public void setGameplayManager(GameplayManager gameplayManager) {
        this.gameplayManager = gameplayManager;
    }

    public MenuScreen getMenuScreen() {
        if(menuScreen == null)
            menuScreen = new MenuScreen(this);

        return menuScreen;
    }

    public void setMenuScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    public GameScreen getGameScreen() {
        if(gameScreen == null)
            gameScreen = new GameScreen(this);

        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public OptionsScreen getOptionsScreen() {
        if(optionsScreen == null)
            optionsScreen = new OptionsScreen(this);

        return optionsScreen;
    }

    public void setOptionsScreen(OptionsScreen optionsScreen) {
        this.optionsScreen = optionsScreen;
    }

    public AboutScreen getAboutScreen() {
        if(aboutScreen == null)
            aboutScreen = new AboutScreen(this);

        return aboutScreen;
    }

    public void setAboutScreen(AboutScreen aboutScreen) {
        this.aboutScreen = aboutScreen;
    }

    public I18NBundle getLangBundle() {
        return langBundle;
    }

    public void setLangBundle(I18NBundle langBundle) {
        this.langBundle = langBundle;
    }


    public PathFinder getOptimalPath() {
        return optimalPath;
    }

    public void setOptimalPath(PathFinder optimalPath) {
        this.optimalPath = optimalPath;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public StarsExplodeEffect getStarsEffect() {
        return starsEffect;
    }

    public void setStarsEffect(StarsExplodeEffect starsEffect) {
        this.starsEffect = starsEffect;
    }

    public DialogBoard getDialogBoard() {
        return dialogBoard;
    }

    public void setDialogBoard(DialogBoard dialogBoard) {
        this.dialogBoard = dialogBoard;
    }
}
