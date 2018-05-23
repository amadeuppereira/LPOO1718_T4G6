package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class SpeedPowerUp extends PowerUpModel {

    private static final int TIME = 1;

    public SpeedPowerUp(float x, float y, RectangleMapObject object){
        super(x,y,object);
        timecount = 0;
    }

    public void action(){
        timecount = TIME;
    }

    public int update(float delta, PlayerBody playerBody){
        if(timecount < 0){
            timecount = 0;
            return 1;
        }
        if(timecount > 0) {
            timecount -= delta;
            playerBody.speedPowerUp();
        }
        return 0;
    }
}
