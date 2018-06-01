package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

/**
 * A model representing a platform.
 */
public class PlatformModel extends EntityModel {

    /**
     * Creates a new platform model in a certain position.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param object the object from the tiled map
     */
    public PlatformModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
    }
}
