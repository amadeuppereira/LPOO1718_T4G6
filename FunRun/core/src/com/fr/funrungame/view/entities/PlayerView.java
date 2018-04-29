package com.fr.funrungame.view.entities;

import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerView extends EntityView {

    private static int currentSkinID  = 0;

    public PlayerView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game) {
        Texture texture = game.getAssetManager().get("player.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public static void setCurrentSkin(int id){
        currentSkinID = id;
    }
}
