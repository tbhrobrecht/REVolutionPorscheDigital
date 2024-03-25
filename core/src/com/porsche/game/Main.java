package com.porsche.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.porsche.game.screens.FirstAdventureScreen;
import com.porsche.game.screens.IntroScreen;
import com.porsche.game.screens.MapScreen;

public class Main extends Game {

	private MapScreen mapScreen;
	private IntroScreen introScreen;
	private FirstAdventureScreen firstAdventureScreen;

	@Override
	public void create() {
		mapScreen = new MapScreen(this);
		introScreen = new IntroScreen(this);
		firstAdventureScreen = new FirstAdventureScreen(this);
		setScreen(firstAdventureScreen);
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		mapScreen.dispose();
	}

	public void goToIntroScreen() {
		this.setScreen(introScreen);
	}

	public void goToFirstAdventureScreen() {
		this.setScreen(firstAdventureScreen);
	}

	public void goToNewFirstAdventureScreen() {
		this.setScreen(new FirstAdventureScreen(this));
	}
}
