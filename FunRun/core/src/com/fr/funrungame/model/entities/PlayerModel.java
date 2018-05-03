package com.fr.funrungame.model.entities;

public class PlayerModel extends EntityModel {

    private boolean running = true;

    private boolean jumping = false;

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

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
