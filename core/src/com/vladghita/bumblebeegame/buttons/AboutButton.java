package com.vladghita.bumblebeegame.buttons;

import com.vladghita.bumblebeegame.utils.AppConstants;

/**
 * Created by Vlad on 04.05.2015.
 */
public class AboutButton extends GenericButton {

    @Override
    public void setButtonText(){
        this.buttonText = game.getLangBundle().get(AppConstants.MENU_ABOUT);
    }

    @Override
    public void buttonAction(){
        game.setScreen(game.getAboutScreen());
    }
}
