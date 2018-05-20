package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class PowerUpModel extends EntityModel{

    RectangleMapObject object;

    public PowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y);
        this.object = object;
    }

    public RectangleMapObject getObject() {
        return object;
    }

    @Override
    public ModelType getType() {
        return ModelType.POWERUP;
    }
}
