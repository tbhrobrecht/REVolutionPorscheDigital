package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.porsche.game.Main;
import com.porsche.game.Stories;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ThurnAndTaxisCastleAdventure; the outline is complete, the story is just missing snippets of content
 */
public class ThurnAndTaxisCastleAdventure extends Stories implements Screen {
    private final Main game;
    private final Texture background = new Texture(Gdx.files.internal("backgrounds/regensburg.jpg"));
    private final String[] dialogueArray = {"Oh look at that beauty. If it isn't the Thurn und Taxis Castle\nin all its glory.",
            "Thurn und Taxis Castle was like the headquarters of\nthe postal pioneers.",
            "Messengers on horseback would dash in and out of the\ncastle, carrying important letters and messages."};
    private final String[] characterNamesArray = {"Johannes", "Johannes", "Christopher"};
    private final List<String> dialogue = new LinkedList<>(Arrays.asList(dialogueArray));
    private final List<String> characterNames = new LinkedList<>(Arrays.asList(characterNamesArray));


    public ThurnAndTaxisCastleAdventure(Main game) {
        this.game = game;
        setFonts();
    }

    @Override
    public void show() {
        setPosition(screenWidth * 0.57f, screenHeight * 0.6f, 3.2f);
    }

    @Override
    public void render(float delta) {
        renderBackground(background);
        renderCharacters(screenWidth * 1.6f, -screenHeight * 0.7f, 0.5f);
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

    }

    @Override
    public void dispose() {
        backgroundSpritebatch.dispose();
        characterSpritebatch.dispose();
        merchantDuoLeftProfileSpritebatch.dispose();
        merchantDuoRightProfileSpritebatch.dispose();
    }
}
