package com.porsche.game;

import com.badlogic.gdx.Game;
import com.porsche.game.screens.DanubeAdventure;
import com.porsche.game.screens.FirstAdventureScreen;
import com.porsche.game.screens.IntroScreen;
import com.porsche.game.screens.MapScreen;

public class Main extends Game {

	private MapScreen mapScreen;
	private IntroScreen introScreen;
	private FirstAdventureScreen firstAdventureScreen;
	private DanubeAdventure danubeAdventure;

	@Override
	public void create() {
		mapScreen = new MapScreen(this);
		introScreen = new IntroScreen(this);
		firstAdventureScreen = new FirstAdventureScreen(this);
		danubeAdventure = new DanubeAdventure(this);
		setScreen(mapScreen);
	}

	@Override
	public void dispose() {

	}

	public void goToMapScreen() {this.setScreen(new MapScreen(this));}

	public void goToIntroScreen() {
		this.setScreen(introScreen);
	}

	public void goToFirstAdventureScreen() {
		this.setScreen(firstAdventureScreen);
	}

	public void goToDanubeAdventure() {this.setScreen(danubeAdventure);}
}
