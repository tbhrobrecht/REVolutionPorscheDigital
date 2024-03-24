package com.porsche.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.porsche.game.screens.MapScreen;

public class Main extends Game {

	private MapScreen mapScreen;

	@Override
	public void create() {
		mapScreen = new MapScreen();
		setScreen(mapScreen);
	}

	@Override
	public void dispose() {
		mapScreen.dispose();
	}
}
