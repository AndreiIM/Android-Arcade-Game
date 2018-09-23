package com.vladghita.bumblebeegame.buttons;

import com.vladghita.bumblebeegame.utils.AppConstants;

/**
 * Created by Vlad on 31.08.2015.
 */
public class OptionsButton extends GenericButton {

    @Override
    public void setButtonText(){
        this.buttonText = game.getLangBundle().get(AppConstants.MENU_OPTIONS);
    }

    @Override
    public void buttonAction(){
        game.setScreen(game.getOptionsScreen());
    }
}
