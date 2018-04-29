package com.fr.funrungame.model.entities;

public class PlayerModel extends EntityModel {

    public PlayerModel(float x, float y){
        super(x,y);
    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
