package com.vladghita.bumblebeegame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.vladghita.bumblebeegame.BumblebeeGame;
//http://stackoverflow.com/questions/16698358/gdxruntimeexception-on-android-couldnt-load-shared-library-gdx-for-target
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BumblebeeGame(), config);
	}
}
