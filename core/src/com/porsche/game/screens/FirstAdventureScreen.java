package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.porsche.game.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class FirstAdventureScreen extends Camera implements Screen {
    private final Main game;
    private final SpriteBatch startingSpriteBatch = new SpriteBatch();
    private final SpriteBatch intermediateSpritebatch = new SpriteBatch();
    private final SpriteBatch endingSpritebatch = new SpriteBatch();
    private final SpriteBatch merchantDuoLeftProfileSpritebatch = new SpriteBatch();
    private final SpriteBatch merchantDuoRightProfileSpritebatch = new SpriteBatch();
    private final SpriteBatch blueProtagonistProfileSpritebatch = new SpriteBatch();
    private final Texture background = new Texture(Gdx.files.internal("backgrounds/village.jpg"));
    private final String[] dialogueArray = {
            "We need protection for our upcoming journey. The roads have\nbecome more treacherous lately.",
            "Aye, and with the rumors of bandit raids, we can't afford\nto take any chances.",
            "[Gasp] Look there, Johannes! They seems capable.\nLet's approach them.",
            "Greetings, good sirs. Christopher and I are in need of\nskilled individuals to accompany us on our journey.\n" +
                    "The roads have become perilous, and we seek brave\nsouls to safeguard our wares.",
            "Protection, eh? What's in it for us?",
            "We offer a fair sum of coin for your services, and\nthe finest accommodations when we reach our\ndestination. Plus, a share of profits from\nsuccessful trades.",
            "Coin and a share of profits, you say? I'm listening.",
            "Indeed. But there's more. We provide you with food,\nand in times of danger, you'll find our gratitude\nas steadfast as our payment.",
            "Consider me interested. What's the plan?",
            "Excellent! We leave at noon. Be ready,\nand we'll ensure your purse is well-lined.",
            "You have a deal, merchants. I'll see you at noon."};

    private final Queue<String> dialogue = new LinkedList<>(Arrays.asList(dialogueArray));
    private String sentence = " ";
    private final String[] characterNamesArray = {"Christopher", "Johannes", "Christopher", "Johannes", "Tommy", "Christopher",
    "Tommy", "Johannes", "Tommy", "Christopher", "Tommy"};
    private final Queue<String> characterNames = new LinkedList<>(Arrays.asList(characterNamesArray));
    private enum Speaker {
        NONE, CHRISTOPHER, JOHANNES, TOMMY
    }
    private Speaker currentSpeaker = Speaker.NONE;
    private String currentCharacter = "";
    private final Characters characters = new Characters();
    private final Protagonists protagonists = new Protagonists();
    private final Dialogue filter = new Dialogue(0.25f, 0.6f);
    private float timer = 0f;
    private final BitmapFont fontName = new BitmapFont();
    private final BitmapFont fontText = new BitmapFont();

    public FirstAdventureScreen(Main game) {
        this.game = game;
        Gdx.input.setInputProcessor(null);
        fontName.getData().setScale(18 / fontName.getCapHeight());
        fontName.setColor(Color.BLACK);
        fontText.getData().setScale(16 / fontText.getCapHeight());
        fontText.setColor(Color.BLACK);
    }

    @Override
    public void show() {
        setPosition(screenWidth * 0.7f, screenHeight * 0.3f, 1f);
    }

    @Override
    public void render(float delta) {
        startingSpriteBatch.setProjectionMatrix(camera.combined);
        startingSpriteBatch.begin();
        startingSpriteBatch.draw(background, (-background.getWidth() / 2f) + screenWidth / 2f, -screenHeight * 1.25f);
        startingSpriteBatch.end();
        filter.renderBackgroundFilter();

        intermediateSpritebatch.setProjectionMatrix(camera.combined);
        intermediateSpritebatch.begin();
        characters.renderMerchantDuo(intermediateSpritebatch, screenWidth * 0.45f, -screenHeight * 0.2f, 0.2f);
        intermediateSpritebatch.end();
        filter.renderTextBubble();

        timer += Gdx.graphics.getDeltaTime();

        if (!dialogue.isEmpty()) {
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && timer >= 0.2f) {
                currentCharacter = characterNames.poll();
                sentence = dialogue.poll();
                if (Objects.equals(currentCharacter, "Christopher")) {
                    currentSpeaker = Speaker.CHRISTOPHER;
                } else if (Objects.equals(currentCharacter, "Johannes")) {
                    currentSpeaker = Speaker.JOHANNES;
                } else if (Objects.equals(currentCharacter, "Tommy")) {
                    currentSpeaker = Speaker.TOMMY;
                } else {
                    currentSpeaker = Speaker.NONE;
                }
                timer = 0f;
            }
        } else {
            game.goToIntroScreen();
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
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.goToIntroScreen();
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        startingSpriteBatch.dispose();
        intermediateSpritebatch.dispose();
        endingSpritebatch.dispose();
        merchantDuoLeftProfileSpritebatch.dispose();
        merchantDuoRightProfileSpritebatch.dispose();
    }
}
