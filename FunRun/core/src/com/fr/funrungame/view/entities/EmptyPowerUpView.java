package com.fr.funrungame.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fr.funrungame.FunRunGame;

public class EmptyPowerUpView extends PowerUpView {

    public EmptyPowerUpView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game){
        Texture texture = game.getAssetManager().get("title.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
