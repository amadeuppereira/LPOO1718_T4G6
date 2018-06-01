package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

/**
 * A model representing a the map endline.
 */
public class EndLineModel extends EntityModel{

    /**
     * Creates a new endline model in a certain position.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param object the object from the tiled map
     */
    public EndLineModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }
}
