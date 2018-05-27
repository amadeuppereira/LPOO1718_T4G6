package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class ShieldPowerUpModel extends PowerUpModel {

    private static final int TIME = 3;

    public ShieldPowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y,object);
        timecount = 0;
    }

    public ShieldPowerUpModel() {
        super();
        timecount = 0;
    }

    public void action(){
        timecount = TIME;
    }

    public int update(float delta, PlayerBody playerBody){
        if(timecount < 0){
            timecount = 0;
            playerBody.shieldPowerUp(false);
            return 1;
        }
        if(timecount > 0) {
            timecount -= delta;
            playerBody.shieldPowerUp(true);
        }
        return 0;
    }
}
