package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class ShieldPowerUpModel extends PowerUpModel {

    private static final int TIME = 3;

    public ShieldPowerUpModel() {
        super();
        timecount = 0;
    }

    @Override
    public void activate(PlayerBody playerBody) {
        if(!playerBody.isShielded()) {
            timecount = TIME;
            action(playerBody);
        }
    }


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

    @Override
    protected void action(PlayerBody playerBody) {
        if(!playerBody.isShielded()) {
            playerBody.shielded(true);
        }
        else
            playerBody.shielded(false);

    }
}
