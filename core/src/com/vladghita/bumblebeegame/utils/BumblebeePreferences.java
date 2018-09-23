package com.vladghita.bumblebeegame.utils;

/**
 * Created by Andrei on 9/19/2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.vladghita.bumblebeegame.drawables.Bumblebee;

public class BumblebeePreferences {

    //trebuie salvat:
    //numele jucatorului
    //sunteul oprit sau nu
    //nivelul
    private static final String PREF_PLAYER_NAME = "PREF_PLAYER_NAME";
    private static final String PREF_LEVEL_CHOSED = "PREF_LEVEL_CHOSED";
    private static final String PREF_SOUND_ENABLED = "PREF_SOUND_ENABLED";
    private static final String PREF_NAME = "PREF_NAME";


    public BumblebeePreferences() {
    }

    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREF_NAME);
    }

    public String playerName() {
        return getPrefs().getString(PREF_PLAYER_NAME, "playerName");
    }

    public void setPlayerName(String playerName) {
        getPrefs().putString(PREF_PLAYER_NAME, playerName);
        getPrefs().flush();
    }

    public boolean isSoundEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEnabled(boolean soundEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEnabled);
        getPrefs().flush();
    }

    public String levelChosed() {
        return getPrefs().getString(PREF_LEVEL_CHOSED,"level");
    }

    public void setLevelChosed(String levelChosed) {
        getPrefs().putString(PREF_LEVEL_CHOSED, levelChosed);
        getPrefs().flush();
    }

}
