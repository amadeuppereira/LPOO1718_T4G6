package com.fr.funrungame.model.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import javax.xml.bind.annotation.XmlType;

/**
 * A model representing a player.
 */
public class PlayerModel extends EntityModel {

    /**
     * The current power up that the player has.
     */
    private PowerUpModel powerup = null;

    /**
     * The player states
     */
    public enum State {DEFAULT, RUNNING, JUMPING, FALLING, DEAD, FINISH};

    /**
     * The player boost effects
     */
    public enum Boost {NONE, SHIELD, INVULNERABLE};

    /**
     * The player state
     */
    private State state;

    /**
     * The player boost
     */
    private Boost boost;

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

        state = State.DEFAULT;
        boost = Boost.NONE;
    }

    /**
     * Gets the player state.
     *
     * @return player state
     */
    public State getState() {
        return state;
    }

    /**
     * Gets the player boost
     *
     * @return th eplayer boost
     */
    public Boost getBoost() {
        return boost;
    }

    /**
     * Sets the player state.
     *
     * @param state the new state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Set the player boost
     *
     * @param boost the new boost
     */
    public void setBoost(Boost boost) {
        this.boost = boost;
    }

    /**
     * Remove the player boost
     */
    public void removeBoost() {
        this.boost = Boost.NONE;
    }

    /**
     * Is the player jumping in this update
     *
     * @return the jumping flag
     */
    public boolean isJumping() {
        return state == State.JUMPING;
    }

    /**
     * Is the player falling in this update
     *
     * @return the falling flag
     */
    public boolean isFalling() {
        return state == State.FALLING;
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
        return state == State.FINISH;
    }

}
