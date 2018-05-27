package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class RocketPowerUpModel extends PowerUpModel {

    private static final int TIME = 1;

    public RocketPowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y,object);
        timecount = 0;
    }

    public RocketPowerUpModel() {
        super();
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
            playerBody.rocketPowerUp();
        }
        return 0;
    }
}
