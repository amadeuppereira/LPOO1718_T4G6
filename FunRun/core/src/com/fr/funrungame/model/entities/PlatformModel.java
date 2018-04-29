package com.fr.funrungame.model.entities;

public class PlatformModel extends EntityModel {

    public PlatformModel(float x, float y){
        super(x,y);
    }

    @Override
    public ModelType getType() {
        return ModelType.PLATFORM;
    }
}
