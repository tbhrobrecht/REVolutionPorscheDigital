package com.porsche.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;
import java.util.Objects;

/**
 * Class meant to act as the blueprint for creating future stories
 */
public class Stories extends Camera {
    protected final SpriteBatch backgroundSpritebatch = new SpriteBatch();
    protected final SpriteBatch characterSpritebatch = new SpriteBatch();
    protected final SpriteBatch merchantDuoLeftProfileSpritebatch = new SpriteBatch();
    protected final SpriteBatch merchantDuoRightProfileSpritebatch = new SpriteBatch();
    protected final SpriteBatch blueProtagonistProfileSpritebatch = new SpriteBatch();
    protected final Characters characters = new Characters();
    protected final Protagonists protagonists = new Protagonists();
    protected String sentence = "";
    protected String currentCharacter = "";
    protected final BitmapFont fontName = new BitmapFont();
    protected final BitmapFont fontText = new BitmapFont();
    protected final Dialogue filter = new Dialogue(0.25f, 0.6f);
    protected Speaker currentSpeaker = Speaker.NONE;
    protected float timer = 0f;
    protected int storyIndex = 0;
    protected int storyLength;
    protected void setFonts() {
        fontName.getData().setScale(18 / fontName.getCapHeight());
        fontName.setColor(Color.BLACK);
        fontText.getData().setScale(16 / fontText.getCapHeight());
        fontText.setColor(Color.BLACK);
    }

    protected void renderBackground(Texture background) {
        backgroundSpritebatch.setProjectionMatrix(camera.combined);
        backgroundSpritebatch.begin();
        backgroundSpritebatch.draw(background, (-background.getWidth() / 2f) + screenWidth / 2f, -screenHeight * 1.25f);
        backgroundSpritebatch.end();
        filter.renderBackgroundFilter();
    }

    protected void renderCharacters(float x, float y, float magnification) {
        characterSpritebatch.setProjectionMatrix(camera.combined);
        characterSpritebatch.begin();
        characters.renderMerchantDuo(characterSpritebatch, x, y, magnification);
        characterSpritebatch.end();
        filter.renderTextBubble();
    }

    protected void renderDialogue(List<String> dialogue, List<String> characterNames) {
        storyLength = dialogue.size();
        timer += Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && timer >= 0.2f) {
            currentCharacter = characterNames.get(storyIndex);
            sentence = dialogue.get(storyIndex);
            if (Objects.equals(currentCharacter, "Christopher")) {
                currentSpeaker = Speaker.CHRISTOPHER;
            } else if (Objects.equals(currentCharacter, "Johannes")) {
                currentSpeaker = Speaker.JOHANNES;
            } else if (Objects.equals(currentCharacter, "Tommy")) {
                currentSpeaker = Speaker.TOMMY;
            } else {
                currentSpeaker = Speaker.NONE;
            }

            if (storyIndex < storyLength - 1) {
                storyIndex++;
            } else {
                storyIndex = 0;
            }
            timer = 0f;
        }

        if (currentSpeaker == Speaker.CHRISTOPHER) {
            merchantDuoLeftProfileSpritebatch.begin();
            characters.renderMerchantDuoLeftProfile(merchantDuoLeftProfileSpritebatch, screenWidth * 0.05f, screenHeight * 0.1f, 0.3f);
            fontName.draw(merchantDuoLeftProfileSpritebatch, currentCharacter, screenWidth * 0.07f, screenHeight * 0.1f);
            fontText.draw(merchantDuoLeftProfileSpritebatch, sentence, screenWidth * 0.25f, screenHeight * 0.4f);
            merchantDuoLeftProfileSpritebatch.end();
        } else if (currentSpeaker == Speaker.JOHANNES) {
            merchantDuoRightProfileSpritebatch.begin();
            characters.renderMerchantDuoRightProfile(merchantDuoRightProfileSpritebatch, screenWidth * 0.05f, screenHeight * 0.1f, 0.3f);
            fontName.draw(merchantDuoRightProfileSpritebatch, currentCharacter, screenWidth * 0.07f, screenHeight * 0.1f);
            fontText.draw(merchantDuoRightProfileSpritebatch, sentence, screenWidth * 0.25f, screenHeight * 0.4f);
            merchantDuoRightProfileSpritebatch.end();
        } else if (currentSpeaker == Speaker.TOMMY) {
            blueProtagonistProfileSpritebatch.begin();
            protagonists.renderBlueProtagonistProfile(blueProtagonistProfileSpritebatch,screenWidth * 0.01f, screenHeight * 0.1f, 0.6f);
            fontName.draw(blueProtagonistProfileSpritebatch, currentCharacter, screenWidth * 0.07f, screenHeight * 0.1f);
            fontText.draw(blueProtagonistProfileSpritebatch, sentence, screenWidth * 0.25f, screenHeight * 0.4f);
            blueProtagonistProfileSpritebatch.end();
        }
    }
}
