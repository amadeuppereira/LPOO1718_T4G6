package com.fr.funrungame.model.entities;

public class PlayerModel extends EntityModel {

    private PowerUpModel powerup = null;

    private boolean running = true;

    private boolean jumping = false;

    private boolean falling = false;

    public PlayerModel(float x, float y){
        super(x,y);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void givePowerup(PowerUpModel pu) {
        if(powerup != null)
            this.powerup = pu;
    }

    public void removePowerup() {
        this.powerup = null;
    }

    public PowerUpModel getPowerup() {
        return this.powerup;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
