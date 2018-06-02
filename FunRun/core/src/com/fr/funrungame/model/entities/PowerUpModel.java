package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

/**
 * A model representing a power up.
 */
public class PowerUpModel extends EntityModel{

    protected double timecount;

    /**
     * Creates a new power up model in a certain position.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     * @param object the object from the tiled map
     */
    public PowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y, object);
        timecount = 0;
    }

    /**
    * Creates a new power up model in a certain position.
    */
    public PowerUpModel() {
        super(-50, -50);
        timecount = 0;
    }

    /**
     * Activates the power up in the playerBody
     *
     * @param playerBody where the power up will be activated
     */
    public void activate(PlayerBody playerBody) { }

    /**
     * Updates the power up in the playerBody
     *
     * @param delta time since last rendered in seconds
     * @param playerBody where the power up will be updated
     */
    public int update(float delta, PlayerBody playerBody) {return 0;}

    /**
     * The power up acts in the playerBody
     *
     * @param playerBody where the power up will act
     */
    public void action(PlayerBody playerBody) {}
}
