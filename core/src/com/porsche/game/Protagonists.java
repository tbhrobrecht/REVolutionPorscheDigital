package com.porsche.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Protagonists class contains the sprites for all the protagonists and renders them
 */
public class Protagonists {
    private final Texture protagonistsSheet = new Texture(Gdx.files.internal("characters/protagonists.png"));
    private final int characterWidth = protagonistsSheet.getWidth() / 4;
    private final int characterHeight = protagonistsSheet.getHeight();
    private final TextureRegion blueProtagonist = new TextureRegion(protagonistsSheet, 0, 0, characterWidth - 20, characterHeight);;
    private final TextureRegion blondeProtagonist = new TextureRegion(protagonistsSheet, 3 * characterWidth + 10, 0, characterWidth, characterHeight);;
    private final TextureRegion brunetteProtagonist = new TextureRegion(protagonistsSheet, characterWidth - 20, 0, characterWidth - 5, characterHeight);;
    private final TextureRegion crownProtagonist = new TextureRegion(protagonistsSheet, 2 * characterWidth, 0, characterWidth, characterHeight);;

    private final TextureRegion blueProtagonistProfile = new TextureRegion(protagonistsSheet, 0, 0, characterWidth - 20, 250);;
//    private final TextureRegion blondeProtagonistProfile;
//    private final TextureRegion brunetteProtagonistProfile;
//    private final TextureRegion crownProtagonistProfile;

    /**
     * Methods render the selected character sprite
     * @param spriteBatch called to draw the sprite
     * @param x sets the x location
     * @param y sets the y location
     * @param magnifier sets the degree of magnification for the sprite
     */
    public void renderBlueProtagonist(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(blueProtagonist, x, y, (float) (protagonistsSheet.getWidth() / 4 - 50) * magnifier, (protagonistsSheet.getHeight() - 100) * magnifier);
    }

    public void renderBrunetteProtagonist(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(brunetteProtagonist, x, y, (characterWidth - 5) * magnifier, characterHeight * magnifier);
    }

    public void renderBlondeProtagonist(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(blondeProtagonist, x, y, characterWidth * magnifier, characterHeight * magnifier);
    }

    public void renderBlueProtagonistProfile(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(blueProtagonistProfile, x, y,(float) (protagonistsSheet.getWidth() / 4 - 50) * magnifier, 0.5f * (500) * magnifier);
    }
}
