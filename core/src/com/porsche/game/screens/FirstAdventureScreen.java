package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.porsche.game.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FirstAdventureScreen extends Stories implements Screen {
    private final Main game;
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
    private final String[] characterNamesArray = {"Christopher", "Johannes", "Christopher", "Johannes", "Tommy", "Christopher",
    "Tommy", "Johannes", "Tommy", "Christopher", "Tommy"};
    private final Queue<String> dialogue = new LinkedList<>(Arrays.asList(dialogueArray));
    private final Queue<String> characterNames = new LinkedList<>(Arrays.asList(characterNamesArray));

    public FirstAdventureScreen(Main game) {
        this.game = game;
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void show() {
        setPosition(screenWidth * 0.7f, screenHeight * 0.3f, 1f);
        setFonts();
    }

    @Override
    public void render(float delta) {
        renderBackground(background);
        renderCharacters(screenWidth * 0.45f, -screenHeight * 0.2f, 0.2f);
        renderDialogue(dialogue, characterNames);

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
        backgroundSpritebatch.dispose();
        characterSpritebatch.dispose();
        merchantDuoLeftProfileSpritebatch.dispose();
        merchantDuoRightProfileSpritebatch.dispose();
    }
}
