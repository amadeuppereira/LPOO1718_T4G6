package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class EnemyModel extends EntityModel {

    public EnemyModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }

    @Override
    public ModelType getType() {
        return ModelType.ENEMY;
    }
}
