package com.porsche.game;

import com.badlogic.gdx.Game;
import com.porsche.game.screens.*;

public class Main extends Game {

	private MapScreen mapScreen;
	private IntroScreen introScreen;
	private FirstAdventureScreen firstAdventureScreen;
	private DanubeAdventure danubeAdventure;
	private ThurnAndTaxisCastleAdventure thurnAndTaxisCastleAdventure;
	private ValleyAdventure valleyAdventure;

	@Override
	public void create() {
		mapScreen = new MapScreen(this);
		introScreen = new IntroScreen(this);
		firstAdventureScreen = new FirstAdventureScreen(this);
		danubeAdventure = new DanubeAdventure(this);
		thurnAndTaxisCastleAdventure = new ThurnAndTaxisCastleAdventure(this);
		valleyAdventure = new ValleyAdventure(this);

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

	public void	goToThurnAndTaxisCastleAdventure() {this.setScreen(thurnAndTaxisCastleAdventure);}

	public void goToValleyAdventure() {this.setScreen(valleyAdventure);}
}
