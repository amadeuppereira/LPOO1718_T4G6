package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class PowerUpModel extends EntityModel{

    double timecount;

    public PowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }

    public PowerUpModel() {
        super(-50, -50);
    }

    public void activate(PlayerBody playerBody) {}

    public int update(float delta, PlayerBody playerBody) {return 0;}

    protected void action(PlayerBody playerBody) {}

    @Override
    public ModelType getType() {
        return ModelType.POWERUP;
    }
}
