package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class PowerUpModel extends EntityModel{

    RectangleMapObject object;

    double timecount;

    public PowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y);
        this.object = object;
    }

    public PowerUpModel() {
        super(-50, -50);
    }

    public void action(){}

    public int update(float delta, PlayerBody playerBody){return 0;}

    public RectangleMapObject getObject() {
        return object;
    }

    @Override
    public ModelType getType() {
        return ModelType.POWERUP;
    }
}
