package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class PlatformModel extends EntityModel {

    RectangleMapObject object;

    public PlatformModel(float x, float y, RectangleMapObject object){
        super(x,y);
        this.object = object;
    }

    public RectangleMapObject getObject() {
        return object;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLATFORM;
    }
}
