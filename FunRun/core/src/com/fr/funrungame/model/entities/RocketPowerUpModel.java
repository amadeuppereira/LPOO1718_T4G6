package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.fr.funrungame.controller.entities.PlayerBody;

public class RocketPowerUpModel extends PowerUpModel {

    private static final int TIME = 1;

    private static final float X_FORCE = 3f;

    private static final float Y_FORCE = 5f;

    public RocketPowerUpModel() {
        super();
        timecount = 0;
    }

    @Override
    public void activate(PlayerBody playerBody) {
        timecount = TIME;
        action(playerBody);
    }

    @Override
    public int update(float delta, PlayerBody playerBody) {
        if(timecount != 0) {
            timecount = 0;
            return 1;
        }

        return 0;
    }

    @Override
    protected void action(PlayerBody playerBody) {
        if(!playerBody.isFinished() && !playerBody.isDead())
            playerBody.applyLinearImpulse(X_FORCE, Y_FORCE);
    }

}
