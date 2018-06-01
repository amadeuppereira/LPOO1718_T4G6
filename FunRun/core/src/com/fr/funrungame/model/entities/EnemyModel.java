package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

/**
 * A model representing an enemy.
 */
public class EnemyModel extends EntityModel {

    /**
     * Creates a new enemy model in a certain position.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param object the object from the tiled map
     */
    public EnemyModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }
}
