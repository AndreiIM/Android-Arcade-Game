package com.vladghita.bumblebeegame.utils;

/**
 * Created by Vlad on 02.04.2015.
 */
public class AppConstants {
    //honeycomb constants
    public static final int HONEYCOMB_SCREEN_MARGIN = 5;  //margin between the screen edges and honeycomb, in pixels
    public static final int HONEYCOMB_MAX_HEX = 11;       //size of honeycomb (max number of hexagons in a row)
    public static final int HONEYCOMB_HEX_CENTER_INDEX_X = 5;   //center hex index X
    public static final int HONEYCOMB_HEX_CENTER_INDEX_Y = 5;   //center hex index Y

    //constants for hneycomb margin points
    public static final int MARGIN_1_X = 213;
    public static final int MARGIN_2_X = 223;
    public static final int MARGIN_3_X = 203;
    public static final int MARGIN_1_Y = 8;

    //constants for point inside polygon methods
    public static final int COLLINEAR_SEGMENT = 0;
    public static final int CLOCKWISE_SEGMENT = 1;
    public static final int COUNTERCLOCKWISE_SEGMENT = 2;
    public static final int INFINITE = 10000;

    public static final int INITIAL_OBSTACLES = 15;          //number of initial obstacles (bees) to be placed
    public static final int MAX_SCORE = 1000;

    //menu constants
    public static final int BUTTONS_COUNT = 4;
    public static final int BUTTON_MENU_SCREEN_MARGIN = 10; //margin between the screen edges and buttons (un menu screen), in pixels
    public static final int BUTTON_MENU_VERTICAL_SPACING_BETWEEN = 5; //spacing between buttons

    //fonts
    public static final String FONT_JOKERMAN = "Jokerman";

    //toast constants
    public static final int TOAST_MAX_TOASTS = 7;
    public static final int TOAST_MARGIN = 6;
    public static final float TOAST_FONT_SCALE = 0.8f;

    //localization and internationalization
    public static final String BUNDLE_I18N_FILE = "i18n/lang";
    public static final String MENU_NEW_GAME="MENU_NEW_GAME";
    public static final String MENU_CONTINUE="MENU_CONTINUE";
    public static final String MENU_ABOUT="MENU_ABOUT";
    public static final String MENU_OPTIONS="MENU_OPTIONS";
    public static final String MENU_EXIT="MENU_EXIT";
    public static final String GAME_FAILED="GAME_FAILED";
    public static final String GAME_WIN="GAME_WIN";
    public static final String SCORE_TOTAL="SCORE_TOTAL";
    public static final String SCORE_STEPS="SCORE_STEPS";
    public static final String OPTION_SOUND_LABEL="OPTION_SOUND_LABEL";
    public static final String PLAYER_NAME_LABEL="PLAYER_NAME_LABEL";
    public static final String OPTION_LEVEL_LABEL="OPTION_LEVEL_LABEL";
    public static final String EASY_LEVEL="EASY_LEVEL";
    public static final String MEDIUM_LEVEL="MEDIUM_LEVEL";
    public static final String HARD_LEVEL="HARD_LEVEL";
    public static final String PLAYER_DEFAULT_NAME="PLAYER_DEFAULT_NAME";
}
