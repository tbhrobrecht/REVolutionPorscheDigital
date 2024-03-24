package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.porsche.game.Main;

public class MapScreen implements Screen {

    private final OrthographicCamera camera = new OrthographicCamera();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture map = new Texture(Gdx.files.internal("map.jpeg"));
    private final Stage stageMapScreen;
    private final float screenWidth = Gdx.graphics.getWidth();
    protected final float screenHeight = Gdx.graphics.getHeight();
    private Music mapScreenBackgroundMusic;

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
                game.goToIntroScreen();
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
        recenter(screenWidth, screenHeight);
        mapScreenBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/MapScreenBackground.wav"));
        mapScreenBackgroundMusic.setLooping(true);
        mapScreenBackgroundMusic.play();
    }

    /**
     * Method used to recenter the map screen interface
     * @param width used to set the width of the screen
     * @param height used to set the height of the screen
     */
    public void recenter(float width, float height) {
        camera.setToOrtho(false, width, height);
        camera.position.set(screenWidth / 2f, screenHeight / 2f, 0);
        camera.zoom = 1.85f;
        camera.update();
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

        handleCameraInput();
        drawStage();
    }

    /**
     * Method that handles the user's keyboard input
     * Capable of shifting the camera's position and zoom
     */
    private void handleCameraInput() {
        float cameraSpeed = 400f * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.R)) recenter(screenWidth, screenHeight);
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.position.y += cameraSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.position.y -= cameraSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.position.x -= cameraSpeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += cameraSpeed;

        float cameraZoom = 0.05f;
        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 5f);
        if (Gdx.input.isKeyPressed(Input.Keys.M)) camera.zoom += cameraZoom;
        if (Gdx.input.isKeyPressed(Input.Keys.N)) camera.zoom -= cameraZoom;
        camera.update();
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
        recenter(width, height);
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
    }

    @Override
    public void dispose() {
        mapScreenBackgroundMusic.dispose();
        spriteBatch.dispose();
        stageMapScreen.dispose();
    }
}
