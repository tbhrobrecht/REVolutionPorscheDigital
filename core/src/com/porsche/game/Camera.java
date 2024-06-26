package com.porsche.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Camera {

    protected final OrthographicCamera camera = new OrthographicCamera();
    protected final float screenWidth = Gdx.graphics.getWidth();
    protected final float screenHeight = Gdx.graphics.getHeight();
    protected final ShapeRenderer shapeRenderer = new ShapeRenderer();
    protected int x, y;

    /**
     * Method used to recenter the map screen interface
     * @param width used to set the width of the screen
     * @param height used to set the height of the screen
     * @param zoom used to set the zoom of the screen, the higher the value, the more zoomed
     */
    protected void recenter(float width, float height, float zoom) {
        camera.setToOrtho(false, width, height);
        camera.position.set(screenWidth / 2f, screenHeight / 2f, 0);
        camera.zoom = zoom;
        camera.update();
    }

    /**
     * Method sets the positioning of the camera
     * @param x used to set the x coordinate
     * @param y used to set the y coordinate
     * @param zoom used to set the zoom of the screen
     */
    protected void setPosition(float x, float y, float zoom) {
        camera.setToOrtho(false, screenWidth, screenHeight);
        camera.position.set(x, y, 0);
        camera.zoom = zoom;
        camera.update();
    }

    /**
     * Method that handles the user's keyboard input
     * Capable of shifting the camera's position and zoom
     */
    protected void handleCameraInput(float zoom) {
        float cameraSpeed = 400f * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.R)) recenter(screenWidth, screenHeight, zoom);
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
     * Method used as a tool to locate the coordinates when creating new stories
     */
    protected void placer() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            System.out.println(camera.position.x);
            System.out.println(camera.position.y);
            System.out.println(screenWidth);
            System.out.println(screenHeight);
            System.out.println(camera.zoom);
        }

        int move = 5;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += move;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= move;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += move;
        pointer(x, y);
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            System.out.println(x);
            System.out.println(y);
        }
    }

    /**
     * Method used as a tool to point towards the coordinates of objects on the screen
     * @param x determines the x coordinate
     * @param y determines the y coordinate
     */
    protected void pointer(int x, int y) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x, y, screenWidth * 0.1f, screenHeight * 0.1f);
        shapeRenderer.end();
    }
}
