package com.vladghita.bumblebeegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vladghita.bumblebeegame.BumblebeeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "BumbleebeeGame";
        config.width = 240;
        config.height = 480;
		new LwjglApplication(new BumblebeeGame(), config);
	}
}
