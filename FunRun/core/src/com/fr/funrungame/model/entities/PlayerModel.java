package com.fr.funrungame.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * A model representing a player.
 */
public class PlayerModel extends EntityModel {

    /**
     * The current power up that the player has.
     */
    private PowerUpModel powerup = null;

    /**
     * Is the player running.
     */
    private boolean running = true;

    /**
     * Is the player jumping.
     */
    private boolean jumping = false;

    /**
     * Is the player falling.
     */
    private boolean falling = false;

    /**
     * Is the player invulnerable.
     */
    private boolean invulnerable = false;

    /**
     * Is the player dead.
     */
    private boolean dead = false;

    /**
     * Is the player shielded.
     */
    private boolean shield = false;

    /**
     * Has the player finished.
     */
    private boolean finished = false;

    /**
     * Picking a power up sound.
     */
    private Music music;

    /**
     * Creates a new player in a certain position.
     *
     * @param x the x-coordinate in meters
     * @param y the y-coordinate in meters
     */
    public PlayerModel(float x, float y){
        super(x,y);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/pickup.wav"));
        music.setVolume(0.1f);
    }

    /**
     * Is the player running in this update
     *
     * @return the running flag
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Set the running flag for this ship
     *
     * @param running the running tag
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Is the player jumping in this update
     *
     * @return the jumping flag
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * Set the jumping flag for this ship
     *
     * @param jumping the jumping tag
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     * Is the player falling in this update
     *
     * @return the falling flag
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * Set the falling flag for this ship
     *
     * @param falling the falling tag
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * Is the player invulnerable in this update
     *
     * @return the invulnerable flag
     */
    public boolean isInvulnerable() {return invulnerable;}

    /**
     * Set the invulnerable flag for this ship
     *
     * @param invulnerable the invulnerable tag
     */
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    /**
     * Is the player dead in this update
     *
     * @return the dead flag
     */
    public boolean isDead() { return dead; }

    /**
     * Set the dead flag for this ship
     *
     * @param dead the dead tag
     */
    public void setDead(boolean dead) { this.dead = dead; }

    /**
     * Is the player shielded in this update
     *
     * @return the shield flag
     */
    public boolean isShield() {
        return shield;
    }

    /**
     * Set the shield flag for this ship
     *
     * @param shield the shield tag
     */
    public void setShield(boolean shield) {
        this.shield = shield;
    }

    /**
     * Gives a power up to this player
     *
     * @param pu new power up
     * @param sound_on is the sound on
     */
    public void givePowerup(PowerUpModel pu, boolean sound_on) {
        if(powerup == null) {
            this.powerup = pu;
            if(sound_on){
                music.play();
            }
        }
    }

    /**
     * Removes the power up from this player
     */
    public void removePowerup() {
        this.powerup = null;
    }

    /**
     * Returns the current power up of this player
     *
     * @return current power up
     */
    public PowerUpModel getPowerup() {
        return this.powerup;
    }

    /**
     * Has the player finished in this update
     *
     * @return the running flag
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Set the finished flag for this ship
     *
     * @param finished the finished tag
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
