package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

/**
 * An abstract model representing an entity belonging to a game model.
 */
public abstract class EntityModel {

    /**
     * The x-coordinate of this model in meters.
     */
    private float x;

    /**
     * The y-coordinate of this model in meters.
     */
    private float y;

    /**
     * The object of this model obtained from the tiled map.
     */
    private RectangleMapObject object;

    /**
     * Constructs a model with a position.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    EntityModel(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a model with a position.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     * @param object The object of this entity
     */
    EntityModel(float x, float y, RectangleMapObject object) {
        this.x = x;
        this.y = y;
        this.object = object;
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the object of this entity.
     *
     * @return The object of this entity.
     */
    public RectangleMapObject getObject() {
        if(object != null)
            return object;
        return null;
    }
}
