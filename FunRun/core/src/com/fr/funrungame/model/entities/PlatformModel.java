package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class PlatformModel extends EntityModel {

    public PlatformModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }

    @Override
    public ModelType getType() {
        return ModelType.PLATFORM;
    }
}
