package com.fr.funrungame.model.entities;

import com.fr.funrungame.controller.entities.PlayerBody;

/**
 * A model representing a rocket power up.
 */
public class RocketPowerUpModel extends PowerUpModel {

    /**
     * Constant of the time the power up will be acting on the player.
     */
    private static final int TIME = 1;

    /**
     * Constant of the X force that will be acting on the player.
     */
    private static final float X_FORCE = 3f;

    /**
     * Constant of the Y force that will be acting on the player.
     */
    private static final float Y_FORCE = 5f;

    /**
     * Creates a new rocket power up model in a certain position.
     */
    public RocketPowerUpModel() {
        super();
        timecount = 0;
    }

    /**
     * Activates the rocket power up in the playerBody
     *
     * @param playerBody where the power up will be activated
     */
    @Override
    public void activate(PlayerBody playerBody) {
        timecount = TIME;
        action(playerBody);
    }

    /**
     * Updates the rocket power up in the playerBody
     *
     * @param delta time since last rendered in seconds
     * @param playerBody where the power up will be updated
     */
    @Override
    public int update(float delta, PlayerBody playerBody) {
        if(timecount != 0) {
            timecount = 0;
            return 1;
        }
        return 0;
    }

    /**
     * The rocket power up acts in the playerBody
     *
     * @param playerBody where the power up will act
     */
    @Override
    protected void action(PlayerBody playerBody) {
        if(!playerBody.isFinished() && !playerBody.isDead())
            playerBody.applyLinearImpulse(X_FORCE, Y_FORCE);
    }

}
