package com.porsche.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * This class is used to draw the dialogue for the characters within the stories
 */
public class Dialogue {
    private ShapeRenderer shapeRenderer;
    private float filterTransparency;
    private float textBubbleTransparency;


    public Dialogue(float filterTransparency, float textBubbleTransparency) {
        this.shapeRenderer = new ShapeRenderer();
        this.filterTransparency = filterTransparency;
        this.textBubbleTransparency = textBubbleTransparency;
    }

    /**
     * Method sprays a transparent layer of white over the entire screen
     */
    public void renderBackgroundFilter() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(1, 1, 1, filterTransparency));
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Method creates the white text box, upon which the text gets printed on
     */
    public void renderTextBubble() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(1, 1, 1, textBubbleTransparency));
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.5f);
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
