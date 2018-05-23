package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class RocketPowerUp extends PowerUpModel {

    private static final int TIME = 2;

    public RocketPowerUp(float x, float y, RectangleMapObject object){
        super(x,y,object);
        timecount = 0;
    }

    public void action(){
        System.out.println("rocket power up");
        timecount = TIME;
    }

    public int update(float delta, PlayerBody playerBody){
        System.out.println(timecount);
        if(timecount < 0){
            timecount = 0;
            return 1;
        }
        if(timecount > 0) {
            timecount -= delta;
            //playerBody.applyLinearImpulse(5,0);
            System.out.println("rocket_action");
        }
        return 0;
    }
}
