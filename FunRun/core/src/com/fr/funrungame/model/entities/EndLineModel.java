package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class EndLineModel extends EntityModel{

    RectangleMapObject object;

    public EndLineModel(float x, float y, RectangleMapObject object){
        super(x,y);
        this.object = object;
    }

    public RectangleMapObject getObject() {
        return object;
    }

    @Override
    public ModelType getType() {
        return ModelType.ENDLINE;
    }
}
