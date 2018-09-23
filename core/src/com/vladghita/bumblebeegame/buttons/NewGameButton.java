package com.vladghita.bumblebeegame.buttons;

import com.vladghita.bumblebeegame.screen.GameScreen;
import com.vladghita.bumblebeegame.screen.LoadingScreen;
import com.vladghita.bumblebeegame.utils.AppConstants;

/**
 * Created by Vlad on 04.05.2015.
 */
public class NewGameButton extends GenericButton {

    @Override
     public void setButtonText(){
        this.buttonText = game.getLangBundle().get(AppConstants.MENU_NEW_GAME);
    }

    public void setButtonText(String buttonText){
        this.buttonText = buttonText;

        this.getButton().setText(buttonText);
    }

    @Override
    public void buttonAction(){
        if(this.buttonText.equals(game.getLangBundle().get(AppConstants.MENU_NEW_GAME)))
            game.getGameplayManager().initNewGame();

        game.setScreen(game.getGameScreen());
    }
}
