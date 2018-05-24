package com.fr.funrungame.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.model.entities.EntityModel;

public class SpeedPowerUpView extends PowerUpView {

    public SpeedPowerUpView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game){
        Texture texture = game.getAssetManager().get("speed.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
