package com.vladghita.bumblebeegame.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.vladghita.bumblebeegame.BumblebeeGame;
import com.vladghita.bumblebeegame.utils.AppConstants;
import com.vladghita.bumblebeegame.utils.AppUtilities;

/**
 * Created by Vlad on 04.05.2015.
 */
public abstract class GenericButton extends Game {

    private TextButton button;
    private TextButton.TextButtonStyle textButtonStyle;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    protected String buttonText;

    protected BumblebeeGame game;

    @Override
    public void create() {

        buttonAtlas = new TextureAtlas(Gdx.files.internal("data/button.atlas"));

        font = new BitmapFont(Gdx.files.internal("data/" + AppConstants.FONT_JOKERMAN + ".fnt"));
        //font.setScale(1f);
        AppUtilities.safeResize(font, 0.5f);
        //font.setColor(new Color(0x000000FF));

        skin = new Skin();
        skin.addRegions(buttonAtlas);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("BUTTON1");
        textButtonStyle.down = skin.getDrawable("BUTTON2");

        setButtonText();
        button = new TextButton(buttonText, textButtonStyle);
        //button.setPosition(10, 100);
        //button.setHeight(50);
        //button.setWidth(200);

        button.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                buttonAction();
            }
        });
    }

    public abstract void buttonAction();

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        skin.dispose();
        buttonAtlas.dispose();
        font.dispose();
    }

    public abstract void setButtonText();

    public TextButton getButton() {
        return button;
    }

    public void setButton(TextButton button) {
        this.button = button;
    }

    public TextButton.TextButtonStyle getTextButtonStyle() {
        return textButtonStyle;
    }

    public void setTextButtonStyle(TextButton.TextButtonStyle textButtonStyle) {
        this.textButtonStyle = textButtonStyle;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public TextureAtlas getButtonAtlas() {
        return buttonAtlas;
    }

    public void setButtonAtlas(TextureAtlas buttonAtlas) {
        this.buttonAtlas = buttonAtlas;
    }

    public float getWidth(){
        return button != null ? button.getWidth() : null;
    }

    public void setWidth(float width){
        if(button != null)
            button.setWidth(width);
    }

    public float getHeight(){
        return button != null ? button.getHeight() : null;
    }

    public void setHeight(float height){
        if(button != null)
            button.setHeight(height);
    }

    public void setPosition(float x, float y){
        if(button != null)
            button.setPosition(x, y);
    }

    public BumblebeeGame getGame() {
        return game;
    }

    public void setGame(BumblebeeGame game) {
        this.game = game;
    }
}
