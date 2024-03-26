package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.porsche.game.Camera;
import com.porsche.game.Characters;
import com.porsche.game.Main;
import com.porsche.game.Protagonists;

public class IntroScreen extends Camera implements Screen {
    private final Main game;
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture background = new Texture(Gdx.files.internal("backgrounds/village.jpg"));
    private final Protagonists protagonists = new Protagonists();
    private final Characters characters = new Characters();
    private final float cameraZoom = 3.25f;
    private float handleInputTimer;

    public IntroScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        recenter(screenWidth, screenHeight, cameraZoom);
    }

    @Override
    public void render(float delta) {
        float green = 0xD2 / 255f;
        float red = 0xC1 / 255f;
        float blue = 0xBC / 255f;

        Gdx.gl.glClearColor(red, green, blue, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(background, (-background.getWidth() / 2f) + screenWidth / 2f, -screenHeight * 1.25f);

        protagonists.renderBrunetteProtagonist(spriteBatch, -screenWidth * 0.3f, -screenHeight, 0.7f);
        protagonists.renderBlondeProtagonist(spriteBatch, -screenWidth * 0.05f, -screenHeight, 0.7f);
        protagonists.renderBlueProtagonist(spriteBatch, -screenWidth * 0.15f, -screenHeight * 1.05f, 0.7f);

        characters.renderMerchantGroup1(spriteBatch, screenWidth, -screenHeight * 1.2f, 1f);
        characters.renderMerchantDuo(spriteBatch, screenWidth * 0.45f, -screenHeight * 0.2f, 0.2f);
        characters.renderMerchantSolo(spriteBatch, -screenWidth * 0.65f, -screenHeight * 0.2f, 0.2f);
        spriteBatch.end();

        handleCameraInput(cameraZoom);

        handleInputTimer += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A) && handleInputTimer >= 1f) {
            game.goToNewFirstAdventureScreen();
            handleInputTimer = 0f;
        }
    }

    @Override
    public void resize(int width, int height) {
        recenter(width, height, cameraZoom);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        background.dispose();
        spriteBatch.dispose();
    }
}
