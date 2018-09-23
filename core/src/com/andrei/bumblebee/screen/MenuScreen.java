package com.andrei.bumblebee.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.andrei.bumblebee.BumblebeeGame;
import com.andrei.bumblebee.buttons.AboutButton;
import com.andrei.bumblebee.buttons.ExitButton;
import com.andrei.bumblebee.buttons.NewGameButton;
import com.andrei.bumblebee.buttons.OptionsButton;
import com.andrei.bumblebee.drawables.MenuScene;
import com.andrei.bumblebee.utils.AppConstants;

/** Intermediate screen which only loads out objects from the game*/
public class MenuScreen extends AbstractScreen {

    private MenuScene img = new MenuScene();

    private Stage stage;
    private AboutButton aboutButton = new AboutButton();
    private NewGameButton newGameButton = new NewGameButton();
    private OptionsButton optionsButton = new OptionsButton();
    private ExitButton exitButton = new ExitButton();

    public MenuScreen(BumblebeeGame game) {
        super(game);

        img.create();

        //compute buttons' dimensions
        int buttonWidth = Gdx.graphics.getWidth() - 2 * AppConstants.BUTTON_MENU_SCREEN_MARGIN;
        int buttonHeight = buttonWidth / 3;
        float buttonVerticalPosition = Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() + buttonHeight - (AppConstants.BUTTONS_COUNT * buttonHeight + ((AppConstants.BUTTONS_COUNT - 1) * AppConstants.BUTTON_MENU_VERTICAL_SPACING_BETWEEN)))/2;
        float verticalCursor = 0;

        //create all the buttons
        verticalCursor = buttonVerticalPosition;
        newGameButton.setGame(game);
        newGameButton.create();
        newGameButton.setHeight(buttonHeight);
        newGameButton.setWidth(buttonWidth);
        newGameButton.setPosition((((float) Gdx.graphics.getWidth()) - newGameButton.getWidth()) / 2, verticalCursor);

        verticalCursor = (buttonVerticalPosition - buttonHeight - AppConstants.BUTTON_MENU_VERTICAL_SPACING_BETWEEN);
        aboutButton.setGame(game);
        aboutButton.create();
        aboutButton.setHeight(buttonHeight);
        aboutButton.setWidth(buttonWidth);
        aboutButton.setPosition((((float) Gdx.graphics.getWidth()) - aboutButton.getWidth()) / 2, verticalCursor);

        verticalCursor = verticalCursor - buttonHeight - AppConstants.BUTTON_MENU_VERTICAL_SPACING_BETWEEN;
        optionsButton.setGame(game);
        optionsButton.create();
        optionsButton.setHeight(buttonHeight);
        optionsButton.setWidth(buttonWidth);
        optionsButton.setPosition((((float) Gdx.graphics.getWidth()) - optionsButton.getWidth()) / 2, verticalCursor);

        verticalCursor = verticalCursor - buttonHeight - AppConstants.BUTTON_MENU_VERTICAL_SPACING_BETWEEN;
        exitButton.setGame(game);
        exitButton.create();
        exitButton.setHeight(buttonHeight);
        exitButton.setWidth(buttonWidth);
        exitButton.setPosition((((float) Gdx.graphics.getWidth()) - exitButton.getWidth()) / 2, verticalCursor);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(newGameButton.getButton());
        stage.addActor(aboutButton.getButton());
        stage.addActor(optionsButton.getButton());
        stage.addActor(exitButton.getButton());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        System.out.println("MenuScreen show");

        Gdx.input.setInputProcessor(stage);   //restore input
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render(float delta) {
        img.render();

        newGameButton.render();
        optionsButton.render();
        aboutButton.render();
        exitButton.render();

        stage.draw();
    }

    @Override
    public void dispose() {
        img.dispose();

        newGameButton.dispose();
        optionsButton.dispose();
        aboutButton.dispose();
        exitButton.dispose();

        stage.dispose();
    }

    @Override
    public void hide() {
        System.out.println("MenuScreen hide");
        //dispose();
        Gdx.input.setInputProcessor(null);  //cancel any input
    }

    public AboutButton getAboutButton() {
        return aboutButton;
    }

    public void setAboutButton(AboutButton aboutButton) {
        this.aboutButton = aboutButton;
    }

    public NewGameButton getNewGameButton() {
        return newGameButton;
    }

    public void setNewGameButton(NewGameButton newGameButton) {
        this.newGameButton = newGameButton;
    }

    public ExitButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(ExitButton exitButton) {
        this.exitButton = exitButton;
    }
}
