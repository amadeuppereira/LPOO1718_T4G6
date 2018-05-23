package com.fr.funrungame.model.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.fr.funrungame.controller.entities.PlayerBody;

public class PowerUpModel extends EntityModel{

    RectangleMapObject object;

    double timecount;

    public PowerUpModel(float x, float y, RectangleMapObject object){
        super(x,y);
        this.object = object;
    }

    public void givePowerUp(PlayerModel playerModel){
        double option = Math.floor(Math.random() * Math.floor(3));
        if(option == 0){
            playerModel.givePowerup(new SpeedPowerUpModel(getX(),getY(),object));
        }
        else if(option == 1){
            playerModel.givePowerup(new ShieldPowerUpModel(getX(),getY(),object));
        }
        else if(option == 2){
            playerModel.givePowerup(new RocketPowerUpModel(getX(),getY(),object));
        }
    }

    public void action(){}

    public int update(float delta, PlayerBody playerBody){return 0;}

    public RectangleMapObject getObject() {
        return object;
    }

    @Override
    public ModelType getType() {
        return ModelType.POWERUP;
    }
}
