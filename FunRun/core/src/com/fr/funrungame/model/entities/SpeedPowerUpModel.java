package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.fr.funrungame.controller.entities.PlayerBody;

public class SpeedPowerUpModel extends PowerUpModel {

    private static final int TIME = 1;

    //private static final float X_FORCE = 1f;
    private static final float X_FORCE = 50f;

    private static final float MAX_VELOCITY = 8f;


    public SpeedPowerUpModel() {
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

    @Override
    protected void action(PlayerBody playerBody) {
        if(!playerBody.isFinished() && !playerBody.isDead())
            //playerBody.applyLinearImpulse(X_FORCE, 0);
            playerBody.applyForce(X_FORCE,0);
    }
}
