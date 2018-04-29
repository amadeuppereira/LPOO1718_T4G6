package com.fr.funrungame.model.entities;

public class EnemyModel extends EntityModel {

    public EnemyModel(float x, float y){
        super(x,y);
    }

    @Override
    public ModelType getType() {
        return ModelType.ENEMY;
    }
}
