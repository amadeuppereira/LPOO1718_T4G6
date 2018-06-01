package com.fr.funrungame.model.entities;

import com.fr.funrungame.controller.entities.PlayerBody;

/**
 * A model representing a shield power up.
 */
public class ShieldPowerUpModel extends PowerUpModel {

    /**
     * Constant of the time the power up will be acting on the player.
     */
    private static final int TIME = 3;

    /**
     * Creates a new shield power up model in a certain position.
     */
    public ShieldPowerUpModel() {
        super();
    }

    /**
     * Activates the shield power up in the playerBody
     *
     * @param playerBody where the power up will be activated
     */
    @Override
    public void activate(PlayerBody playerBody) {
        if(!playerBody.isShielded()) {
            timecount = TIME;
            action(playerBody);
        }
    }

    /**
     * Updates the shield power up in the playerBody
     *
     * @param delta time since last rendered in seconds
     * @param playerBody where the power up will be updated
     */
    @Override
    public int update(float delta, PlayerBody playerBody) {
        if (timecount < 0) {
            timecount = 0;
            action(playerBody);
            return 1;
        }

        else if (timecount > 0) {
            timecount -= delta;
        }

        return 0;
    }

    /**
     * The shield power up acts in the playerBody
     *
     * @param playerBody where the power up will act
     */
    @Override
    public void action(PlayerBody playerBody) {
        if(!playerBody.isShielded()) {
            playerBody.shielded(true);
        }
        else
            playerBody.shielded(false);

    }
}
