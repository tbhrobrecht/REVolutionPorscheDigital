package com.porsche.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.porsche.game.Main;
import com.porsche.game.Stories;

import java.util.*;

public class DanubeAdventure extends Stories implements Screen {
    private final Main game;
    private final Texture background = new Texture(Gdx.files.internal("backgrounds/danubeRiverside.jpg"));
    private final String[] dialogueArray = {"Ah, the Danube, brings back memories of\nour carefree days.",
            "Indeed, Johannes. Do you remember when we were\nbut small lads playing along these very banks?",
            "I recall those endless days of mischief and laughter.\nThe Danube was our playground, a river of adventures.",
            "We'd skip stones, chase after butterflies, and\nimagine ourselves as knights guarding its shores.",
            "And the stories we'd make up! The river carried\nour dreams like secret messages to distant lands.",
            "Hey, what is the Danube?",
            "What? You don't know? It is the river right next to us!",
            "Oh, is there anything special about it?",
            "Anything special about it? What isn't special about\nit? When we were younger, we would always\ncome here to play.",
            "And aside from our personal feelings, the river carries\na plethora of other remarkable characteristics.",
            "Oh really? Lets hear some of them.", // have the mother do this
            "Well firstly, the Danube was like a superhighway\nfor trade. Merchants on boats carried goods from\none place to another.",
            "Oh, just trade?",
            "[Chuckle] No, not just trade, it is a river of\nmusic too!",
            "Minstrels play tunes, and the river itself makes\na rhythmic melody. Close your eyes, and you might\nhear the natural symphony; " +
                    "the splash of\noars and the gentle lap of water against boats.",
            "Lastly, the Danube is a river of romance.",
            "Knights and princesses would stroll by its banks,\nand minstrels would sing love songs. It's as\nif the river itself whispered love stories to\neveryone passing by.",
            "There are thousands of rivers on this continent, yet\nnone quite flow like this one."};
    private final String[] characterNamesArray = {"Johannes", "Christopher", "Johannes", "Christopher", "Johannes",
            "Tommy", "Christopher", "Tommy", "Johannes", "Christopher", "Tommy", "Johannes", "Tommy", "Johannes", "Christopher",
            "Christopher", "Christopher", "Johannes"};
    private final List<String> dialogue = new ArrayList<>(Arrays.asList(dialogueArray));
    private final List<String> characterNames = new ArrayList<>(Arrays.asList(characterNamesArray));


    public DanubeAdventure(Main game) {
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
        renderCharacters(screenWidth * 1.8f, -screenHeight * 0.2f, 0.18f);
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

    }
}
