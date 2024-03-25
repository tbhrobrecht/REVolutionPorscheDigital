package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.porsche.game.Camera;
import com.porsche.game.Main;

public class MapScreen extends Camera implements Screen {

    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture map = new Texture(Gdx.files.internal("backgrounds/map.jpeg"));
    private final Stage stageMapScreen;
    private Music mapScreenBackgroundMusic;
    private final float cameraZoom = 1.85f;

    public MapScreen(final Main game) {
        Skin skin = new Skin(Gdx.files.internal("craft/craftacular-ui.json"));
        this.stageMapScreen = new Stage(new FitViewport(screenWidth, screenHeight));
        Gdx.input.setInputProcessor(stageMapScreen);


        Table table = new Table();
        table.setFillParent(true);

        TextButton startButton = new TextButton("Start", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToIntroScreen();
            }
        });

        TextButton avatarButton = new TextButton("Avatars", skin);
        avatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToIntroScreen();
            }
        });

        TextButton settingsButton = new TextButton("Settings", skin);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToIntroScreen();
            }
        });

        TextButton exitButton = new TextButton("Quit", skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        float buttonWidth = screenWidth * 0.3f;
        float buttonHeight = screenHeight * 0.15f;
        float padding = 10f;

        table.add(startButton).padBottom(padding).width(buttonWidth).height(buttonHeight).row();
        table.add(avatarButton).padBottom(padding).width(buttonWidth).height(buttonHeight).row();
        table.add(settingsButton).padBottom(padding).width(buttonWidth).height(buttonHeight).row();
        table.add(exitButton).width(buttonWidth).height(buttonHeight);

        table.setPosition(-screenWidth * 0.3f, -screenHeight * 0.1f);
        stageMapScreen.addActor(table);
    }

    @Override
    public void show() {
        recenter(screenWidth, screenHeight, cameraZoom);
        mapScreenBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/MapScreenBackground.wav"));
        mapScreenBackgroundMusic.setLooping(true);
        mapScreenBackgroundMusic.play();
    }

    @Override
    public void render(float delta) {
        float red = 0xC1 / 255f;
        float green = 0xD2 / 255f;
        float blue = 0xBC / 255f;

        Gdx.gl.glClearColor(red, green, blue, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(map, (-map.getWidth() / 2f) + screenWidth / 2f, (-map.getHeight() / 2f) + screenHeight / 2f);
        spriteBatch.end();

        handleCameraInput(cameraZoom);
        drawStage();
    }


    /**
     * Method responsible for drawing the Menu
     */
    private void drawStage() {
        stageMapScreen.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stageMapScreen.draw();
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
        mapScreenBackgroundMusic.stop();
        mapScreenBackgroundMusic.dispose();
        stageMapScreen.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (mapScreenBackgroundMusic != null) mapScreenBackgroundMusic.dispose();
        spriteBatch.dispose();
        stageMapScreen.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
