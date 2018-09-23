package com.andrei.bumblebee.buttons;

import com.badlogic.gdx.Gdx;
import com.andrei.bumblebee.utils.AppConstants;

/**
 * Created by Vlad on 04.05.2015.
 */
public class ExitButton extends GenericButton {

    @Override
    public void setButtonText(){
        this.buttonText = game.getLangBundle().get(AppConstants.MENU_EXIT);
    }

    @Override
    public void buttonAction(){
        Gdx.app.exit();
    }
}
