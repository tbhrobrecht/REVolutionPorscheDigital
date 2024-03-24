package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.security.Key;

public class MapScreen implements Screen {

    private final OrthographicCamera camera = new OrthographicCamera();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Texture map = new Texture(Gdx.files.internal("map.jpeg"));

    @Override
    public void show() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        float red = 0xC1 / 255f;
        float green = 0xD2 / 255f;
        float blue = 0xBC / 255f;

        Gdx.gl.glClearColor(red, green, blue, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(map, (-map.getWidth() / 2f) + Gdx.graphics.getWidth() / 2f, (-map.getHeight() / 2f) + Gdx.graphics.getHeight() / 2f);
        spriteBatch.end();
    }

    private void handleInput() {
        float cameraSpeed = 400f * Gdx.graphics.getDeltaTime();
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

    @Override
    public void resize(int width, int height) {

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

    }
}
