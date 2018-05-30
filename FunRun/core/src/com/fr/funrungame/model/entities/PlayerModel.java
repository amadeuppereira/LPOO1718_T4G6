package com.fr.funrungame.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class PlayerModel extends EntityModel {

    private PowerUpModel powerup = null;

    private boolean running = true;

    private boolean jumping = false;

    private boolean falling = false;

    private boolean invulnerable = false;

    private boolean dead = false;

    private boolean shield = false;

    private boolean finished = false;

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

    public boolean isInvulnerable() {return invulnerable;}

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public boolean isDead() { return dead; }

    public void setDead(boolean dead) { this.dead = dead; }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public void givePowerup(PowerUpModel pu, boolean sound_on) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pickup.wav"));
        if(powerup == null) {
            this.powerup = pu;
            if(sound_on){
                sound.play(1.0f);
            }
        }
    }

    public void removePowerup() {
        this.powerup = null;
    }

    public PowerUpModel getPowerup() {
        return this.powerup;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLAYER;
    }
}
