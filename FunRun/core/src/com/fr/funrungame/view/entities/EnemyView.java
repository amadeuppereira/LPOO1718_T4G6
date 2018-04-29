package com.fr.funrungame.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fr.funrungame.FunRunGame;

public class EnemyView extends EntityView {

    public EnemyView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game) {
        Texture texture = game.getAssetManager().get("player.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}