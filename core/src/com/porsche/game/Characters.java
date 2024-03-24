package com.porsche.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Characters {
    private final Texture merchantGroup1Sheet = new Texture(Gdx.files.internal("characters/merchantGroup1.png"));
    private final int merchantGroup1Width = 525;
    private final int merchantGroup1Height = 500;
    private final TextureRegion merchantGroup1 = new TextureRegion(merchantGroup1Sheet, 0, 0, merchantGroup1Width, merchantGroup1Height);

    private final Texture merchantGroup2Sheet = new Texture(Gdx.files.internal("characters/merchantGroup2.png"));
    private final int merchantSoloWidth = 650;
    private final int merchantDuoWidth = 980;
    private final int merchantGroup2Height = 1500;
    private final int merchantGroup2ProfileHeight = 500;
    private final TextureRegion merchantSolo = new TextureRegion(merchantGroup2Sheet, 0, 0, merchantSoloWidth, merchantGroup2Height);
    private final TextureRegion merchantDuo = new TextureRegion(merchantGroup2Sheet, merchantSoloWidth, 0, merchantDuoWidth, merchantGroup2Height);
    private final TextureRegion merchantDuoProfile = new TextureRegion(merchantGroup2Sheet, merchantSoloWidth, 0, merchantDuoWidth, merchantGroup2ProfileHeight);

    public void renderMerchantGroup1(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(merchantGroup1, x, y, merchantGroup1Width * magnifier, merchantGroup1Height * magnifier);
    }

    public void renderMerchantSolo(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(merchantSolo, x, y, merchantSoloWidth * magnifier, merchantGroup2Height * magnifier);
    }

    public void renderMerchantDuo(SpriteBatch spriteBatch, float x, float y, float magnifier) {
        spriteBatch.draw(merchantDuo, x, y, merchantDuoWidth * magnifier, merchantGroup2Height * magnifier);
    }
}
