package com.fr.funrungame.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.model.entities.EntityModel;

public class ShieldPowerUpView extends PowerUpView {

    public ShieldPowerUpView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game){
        Texture texture = game.getAssetManager().get("shield.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
