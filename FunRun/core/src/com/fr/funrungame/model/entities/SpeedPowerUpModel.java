package com.fr.funrungame.model.entities;

import com.fr.funrungame.controller.entities.PlayerBody;

/**
 * A model representing a speed power up.
 */
public class SpeedPowerUpModel extends PowerUpModel {

    /**
     * Constant of the time the power up will be acting on the player.
     */
    private static final int TIME = 1;

    /**
     * Constant of the X force that will be acting on the player.
     */
    private static final float X_FORCE = 50f;

    /**
     * Constant of the max velocity of the player.
     */
    private static final float MAX_VELOCITY = 8f;

    /**
     * Creates a new speed power up model in a certain position.
     */
    public SpeedPowerUpModel() {
        super();
        timecount = 0;
    }

    /**
     * Activates the speed power up in the playerBody
     *
     * @param playerBody where the power up will be activated
     */
    @Override
    public void activate(PlayerBody playerBody) {
        timecount = TIME;
        action(playerBody);
    }

    /**
     * Updates the speed power up in the playerBody
     *
     * @param delta time since last rendered in seconds
     * @param playerBody where the power up will be updated
     */
    @Override
    public int update(float delta, PlayerBody playerBody) {
        if(timecount < 0){
            timecount = 0;
            return 1;
        }
        else if (timecount > 0) {
            timecount -= delta;
            if (playerBody.getVelX() <= MAX_VELOCITY)
                action(playerBody);
        }
        return 0;
    }

    /**
     * The speed power up acts in the playerBody
     *
     * @param playerBody where the power up will act
     */
    @Override
    protected void action(PlayerBody playerBody) {
        if(!playerBody.isFinished() && !playerBody.isDead())
            playerBody.applyForce(X_FORCE,0);
    }
}
