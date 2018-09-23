package com.vladghita.bumblebeegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.drawables.OptionsScene;
import com.vladghita.bumblebeegame.utils.AppConstants;
import com.vladghita.bumblebeegame.utils.AppUtilities;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.vladghita.bumblebeegame.utils.BumblebeePreferences;


/** Intermediate screen which only loads out objects from the game*/
public class OptionsScreen extends AbstractScreen implements InputProcessor {

    private static final Color TEXTFIELD_COLOR = new Color(0xA36D0F5A);

    private OptionsScene img = new OptionsScene();

    private BumblebeePreferences pref = new BumblebeePreferences();
    private InputMultiplexer im;
    private Stage stage;

    private Skin skin;
    private Table table;

    TextField playerNameTextfield;
    Label playerNameLabel;

    Label soundEnabledLabel;
    CheckBox soundEnabledCheckBox;

    TextField levelLabelTextfield;
    Label levelLabel;
    SelectBox<String> levelSelectBox;


    public OptionsScreen(BumblebeeGame game) {
        super(game);



        img.create();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.setTransform(true);
        table.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        AppUtilities.safeResize(table, 0.5f);
        table.setFillParent(true);
        //table.setDebug(true);
        table.center();

        playerNameLabel = new Label(game.getLangBundle().get(AppConstants.PLAYER_NAME_LABEL), skin);
        table.add(playerNameLabel).colspan(2).row();

        playerNameTextfield = new TextField("", skin);
        playerNameTextfield.setMessageText(game.getLangBundle().get(AppConstants.PLAYER_DEFAULT_NAME));
        playerNameTextfield.setColor(TEXTFIELD_COLOR);
        table.add(playerNameTextfield).colspan(2).row();
        playerNameTextfield.setText("Player1");
        table.add(" ").row(); //empty row

        levelLabel = new Label(game.getLangBundle().get(AppConstants.OPTION_LEVEL_LABEL), skin);
        table.add(levelLabel);

        SelectBox.SelectBoxStyle boxStyle = new SelectBox.SelectBoxStyle();
        String[] levels = { game.getLangBundle().get(AppConstants.EASY_LEVEL),
                            game.getLangBundle().get(AppConstants.MEDIUM_LEVEL),
                            game.getLangBundle().get(AppConstants.HARD_LEVEL)
                          };

        levelSelectBox = new SelectBox(skin);
        levelSelectBox.setItems(levels);

        table.add(levelSelectBox).row();

        table.add(" ").row(); //empty row

        soundEnabledLabel = new Label(game.getLangBundle().get(AppConstants.OPTION_SOUND_LABEL), skin);
        table.add(soundEnabledLabel);

        soundEnabledCheckBox = new CheckBox("", skin);
        table.add(soundEnabledCheckBox).row();

        table.add(" ").row(); //empty row

        stage.addActor(table);
        im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(stage);
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
        System.out.println("OptionsScreen show");

        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        img.render();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        img.dispose();
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void hide() {
        System.out.println("OptionsScreen hide");
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
