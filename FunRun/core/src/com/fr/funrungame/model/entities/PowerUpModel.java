package com.fr.funrungame.model.entities;

public class PowerUpModel extends EntityModel{

    public PowerUpModel(float x, float y){
        super(x,y);
    }

    @Override
    public ModelType getType() {
        return ModelType.POWERUP;
    }
}
