package com.fr.funrungame.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.model.entities.EntityModel;

public class PowerUpView extends EntityView {

    float x;

    float y;

    public PowerUpView(FunRunGame game){
        super(game);
    }

    public Sprite createSprite(FunRunGame game){return new Sprite();}

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(EntityModel model) {}

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, x, y, 100,100);
    }
}