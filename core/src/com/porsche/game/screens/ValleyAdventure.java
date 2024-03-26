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

public class ValleyAdventure extends Stories implements Screen {
    private final Main game;
    private final Texture background = new Texture(Gdx.files.internal("backgrounds/valley.jpg"));
    private final String[] dialogueArray = {"So, if you know Croatian, you might understand some\nwords in Slavic languages too, like secret codes\nthat connect friends across different lands.",
            "How do the languages in this region differ from\none another?"};
    private final String[] characterNamesArray = {"Johannes", "Tommy"};
    private final List<String> dialogue = new LinkedList<>(Arrays.asList(dialogueArray));
    private final List<String> characterNames = new LinkedList<>(Arrays.asList(characterNamesArray));

    public ValleyAdventure(Main game) {
        this.game = game;
        setFonts();
    }

    @Override
    public void show() {
        setPosition(screenWidth * 0.5f, screenHeight * 0.4f, 3.2f);
    }

    @Override
    public void render(float delta) {
        renderBackground(background);
        renderCharacters(screenWidth * 0.45f, -screenHeight * 1.25f, 0.5f);
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
