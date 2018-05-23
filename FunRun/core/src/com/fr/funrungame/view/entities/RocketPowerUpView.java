package com.fr.funrungame.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fr.funrungame.FunRunGame;

public class RocketPowerUpView extends PowerUpView {

    public RocketPowerUpView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game){
        Texture texture = game.getAssetManager().get("rocket.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
