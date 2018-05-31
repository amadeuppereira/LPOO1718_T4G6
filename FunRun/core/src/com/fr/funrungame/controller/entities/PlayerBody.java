package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

public class PlayerBody extends EntityBody {

    /**
     * The player acceleration
     */
    private final float ACCELERATION = 15f;
    /**
     * The player jump force
     */
    private final float JUMP_FORCE = 5f;
    /**
     * The player climb force
     */
    private final float CLIMB_FORCE = 1.5f;
    /**
     * The player downwards force
     */
    private final float DOWN_FORCE = -100f;
    /**
     * The player death time
     */
    private final int DEAD_TIME = 1;
    /**
     * The player invulnerable time
     */
    private final int INVULNERABLE_TIME = 3;
    /**
     * The player dead state
     */
    private boolean DEAD = false;
    /**
     * The player invulnerable state
     */
    private boolean INVULNERABLE = false;
    /**
     * The player finish state
     */
    private boolean FINISHED = false;
    /**
     * The player shield state
     */
    private boolean SHIELD = false;

    /**
     * The death timer
     */
    private float death_timer = 0;
    /**
     * The invulnerability timer
     */
    private float invulnerable_timer = 0;
    /**
     * The player playing time
     */
    private float time;
    /**
     * Sound effect for jumping/climbing
     */
    private Music music;

    /**
     * Constructs a player body representing a model in a certain world.
     * @param world world
     * @param model model
     */
    public PlayerBody(World world, EntityModel model) {
        super();
        createBody(world, model, BodyDef.BodyType.DynamicBody);
        createFixture(model);
        time = 0;
        getSounds();
    }

    /**
     * Load sound effects
     */
    private void getSounds() {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/jump.wav"));
        music.setVolume(0.1f);
    }

    @Override
    protected void createBody(World world, EntityModel model, BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());

        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    @Override
    protected FixtureDef getFixtureDef(EntityModel model) {
        PolygonShape shape = new PolygonShape();
        FixtureDef fixturedef = new FixtureDef();

        shape.setAsBox((55 / 2) * PIXEL_TO_METER, (84 / 2) * PIXEL_TO_METER);
        fixturedef.shape = shape;
        fixturedef.filter.categoryBits = PLAYER_BODY;
        fixturedef.filter.maskBits = TERRAIN_BODY;

        return fixturedef;
    }

    @Override
    protected void createFixture(EntityModel model) {
        body.createFixture(getFixtureDef(model));
    }

    /**
     * Update the player body with a given delta time
     * @param delta time since last rendered in seconds
     */
    public void update(float delta) {

        accelerateX(delta);
        checkFinishState(delta);
        updateDeadState(delta);
        updateInvulnerableState(delta);
        updatePowerup(delta);

        updateModel();

    }

    /**
     * Accelerates the player in the X coordinate
     * @param delta time since last rendered in seconds
     */
    private void accelerateX(float delta) {
        if(getVelX() <= 5 && getVelX() > 0 && !FINISHED && !DEAD) {
            body.setLinearVelocity(getVelX() + ACCELERATION * delta, getVelY());
        }
        else if(getVelX() == 0 && !DEAD && !FINISHED) {
            body.applyLinearImpulse(new Vector2(0.05f, 0f), body.getWorldCenter(), true);
        }
    }

    /**
     * Updates the powerup
     * @param delta time since last rendered in seconds
     */
    private void updatePowerup(float delta) {

        if (!FINISHED && ((PlayerModel) getUserData()).getPowerup() != null) {
            if (((PlayerModel) getUserData()).getPowerup().update(delta, this) == 1) {
                ((PlayerModel) getUserData()).removePowerup();
            }
        }
    }

    /**
     * Checks if the player has finished the run, updating him accordingly
     * @param delta time since last rendered in seconds
     */
    private void checkFinishState(float delta) {
        if(!FINISHED)
            time += delta;
        else {
            if(getVelX() > 0)
                body.setLinearVelocity(getVelX() - ACCELERATION/2 * delta, getVelY());
            if(getVelX() < 0)
                body.setLinearVelocity(0, getVelY());
        }
    }

    /**
     * Updates the player dead state
     * @param delta time since last rendered in seconds
     */
    private void updateDeadState(float delta) {
        if(DEAD) {
            death_timer -= delta;
            if(death_timer <= 0) setDead(false);
        }
    }

    /**
     * Updates the player invulnerable state
     * @param delta time since last rendered in seconds
     */
    private void updateInvulnerableState(float delta) {
        if(INVULNERABLE) {
            invulnerable_timer -= delta;
            if(invulnerable_timer <= 0) setInvulnerable(false);
        }
    }

    /**
     * Updates the player model
     */
    private void updateModel() {
        updateRunningState();
        updateJumpingState();
        updateFallingState();
    }

    /**
     * Updates the player running state
     */
    private void updateRunningState() {
        if (getVelX() == 0) {
            ((PlayerModel) getUserData()).setRunning(false);
        } else {
            ((PlayerModel) getUserData()).setRunning(true);
        }
    }

    /**
     * Updates the player jumping state
     */
    private void updateJumpingState() {
        if (getVelY() > 0) {
            ((PlayerModel) getUserData()).setJumping(true);
            ((PlayerModel) getUserData()).setFalling(false);
        }
    }

    /**
     * Updates the player falling state
     */
    private void updateFallingState() {
        if (getVelY() < 0) {
            ((PlayerModel) getUserData()).setJumping(false);
            ((PlayerModel) getUserData()).setFalling(true);
        }
    }

    /**
     * Applies the jumping/climbing force to the player
     * @param sound flag indicating if sound is played or not
     */
    public void jump(boolean sound) {
        if(DEAD || FINISHED) return;
        if(getVelY() == 0) {//jump
            body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
        }
        else if (getVelX() < 0.1f && getVelY() < 4) {
            body.applyLinearImpulse(new Vector2(0, CLIMB_FORCE), body.getWorldCenter(), true);

        }

        if(sound)
            music.play();

    }

    /**
     * Applies a downwards force to the player
     */
    public void moveDown(){
        if(DEAD || FINISHED) return;
        if(((PlayerModel) getUserData()).isJumping() || ((PlayerModel) getUserData()).isFalling())
            body.applyForceToCenter(0, DOWN_FORCE, true);
    }

    /**
     * Stops the player movement
     */
    private void stop() {
        body.setLinearVelocity(new Vector2(0,0));
    }

    /**
     * Sets the player invulnerable state
     * @param invulnerable boolean value to update the state
     */
    private void setInvulnerable(boolean invulnerable) {
        if(invulnerable) {
            invulnerable_timer = INVULNERABLE_TIME;
            INVULNERABLE = true;
            ((PlayerModel)getUserData()).setInvulnerable(true);
        }
        else {
            INVULNERABLE = false;
            ((PlayerModel)getUserData()).setInvulnerable(false);
        }
    }

    /**
     * Sets the player dead state
     * @param dead boolean value to update the state
     */
    private void setDead(boolean dead) {
        if(dead) {
            death_timer = DEAD_TIME;
            DEAD = true;
            ((PlayerModel)getUserData()).setDead(true);
        }
        else {
            DEAD = false;
            ((PlayerModel)getUserData()).setDead(false);
        }
    }

    /**
     * Makes the player die
     */
    public void die() {
        if(INVULNERABLE || DEAD || FINISHED || SHIELD) return;
        stop();
        setDead(true);
        setInvulnerable(true);
    }

    /**
     * Sets the player finished state
     */
    public void setFinish() {
        time = GameController.getInstance().getTime();
        FINISHED = true;
        ((PlayerModel)getUserData()).setFinished(true);
    }

    /**
     * Updates the player shielded state
     * @param shield boolean value to update the state
     */
    public void shielded(boolean shield){
        SHIELD = shield;
        ((PlayerModel)getUserData()).setShield(shield);
    }

    /**
     * Activates the player's powerup
     */
    public void usePowerUp() {
        if(((PlayerModel) body.getUserData()).getPowerup() != null){
            ((PlayerModel) body.getUserData()).getPowerup().activate(this);
        }
    }

    /**
     * Get the finished state
     * @return the finished state
     */
    public boolean isFinished() {
        return FINISHED;
    }

    /**
     * Get the dead state
     * @return the dead state
     */
    public boolean isDead() { return DEAD;}

    /**
     * Get the time whilst the player is player or the finish time
     * @return
     */
    public float getTime() {
        return time;
    }

    /**
     * Get the player shielded state
     * @return the shielded state
     */
    public boolean isShielded() {
        return SHIELD;
    }

}
