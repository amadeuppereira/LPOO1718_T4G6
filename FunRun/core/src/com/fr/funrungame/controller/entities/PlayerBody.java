package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.model.entities.ShieldPowerUpModel;

import java.util.ArrayList;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

public class PlayerBody extends EntityBody {

    private float RUN_FORCE = 15f;
    private float JUMP_FORCE = 5f;
    private float CLIMB_FORCE = 1.5f;
    private float DOWN_FORCE = -100f;
    private boolean DEAD = false;
    private boolean INVULNERABLE = false;
    private boolean FINISHED = false;
    private boolean SHIELD = false;

    private int DEAD_TIME = 1;
    private int INVULNERABLE_TIME = 2;

    private float death_timer = 0;
    private float invulnerable_timer = 0;

    private float time;

    public PlayerBody(World world, EntityModel model) {
        super();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());


        body = world.createBody(bodyDef);
        body.setUserData(model);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(55/2 * PIXEL_TO_METER, 84/2 * PIXEL_TO_METER);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        fixturedef.filter.categoryBits = PLAYER_BODY;
        fixturedef.filter.maskBits = TERRAIN_BODY;
        body.createFixture(fixturedef);

        time = 0;

    }

    public void update(float delta) {
        if(!FINISHED)
            time += delta;

        if(FINISHED){
            if(body.getLinearVelocity().x  < 0.1)
                body.setLinearVelocity(new Vector2(0,0));
            else
                body.setLinearVelocity(new Vector2(body.getLinearVelocity().x * 0.9f,-5));
        }

        if(DEAD) {
            death_timer -= delta;
            if(death_timer <= 0) setDead(false);
        }

        if(INVULNERABLE) {
            invulnerable_timer -= delta;
            if(invulnerable_timer <= 0) setInvulnerable(false);
        }

    }

    public void jump(boolean sound_on) {
        if(DEAD || FINISHED) return;
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/jump.wav"));
        music.setVolume(0.1f);
        if(body.getLinearVelocity().y == 0) {//jump
            body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
            if(sound_on){
                music.play();
            }
        }
        else if (body.getLinearVelocity().x == 0 && body.getLinearVelocity().y < 4) {
            body.applyLinearImpulse(new Vector2(0, CLIMB_FORCE), body.getWorldCenter(), true);
            if(sound_on){
                music.play();
            }
        }
    }

    public void moveDown(){
        if(DEAD || FINISHED) return;
        if(((PlayerModel) getUserData()).isJumping() || ((PlayerModel) getUserData()).isFalling())
            body.applyForceToCenter(0, DOWN_FORCE, true);
    }

    public void run() {
        if(DEAD) return;
        else body.applyForceToCenter(RUN_FORCE,0, true);
    }

    public void stop() {
        body.setLinearVelocity(new Vector2(0,0));
    }

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

    public void die() {
        if(INVULNERABLE || DEAD || FINISHED || SHIELD) return;
        stop();
        setDead(true);
        setInvulnerable(true);
    }

    public void setFinish() {
        time = GameController.getInstance().getTime();
        FINISHED = true;
        ((PlayerModel)getUserData()).setFinished(true);
    }

    public void speedPowerUp(){
        if(FINISHED){
            stop();
            return;
        }
        if(body.getLinearVelocity().x <= 8)
            body.applyLinearImpulse(new Vector2(1,0), body.getWorldCenter(),true);

    }

    public void rocketPowerUp(){
        if(FINISHED || DEAD){
            stop();
            return;
        }
        if(body.getLinearVelocity().x <= 8 && body.getLinearVelocity().y <= 8)
            body.applyLinearImpulse(new Vector2(1,1.5f), body.getWorldCenter(),true);
    }

    public void shieldPowerUp(boolean shield){
        SHIELD = shield;
        ((PlayerModel)getUserData()).setShield(shield);
    }

    public void usePowerUp() {
        if(((PlayerModel) body.getUserData()).getPowerup() != null){
            ((PlayerModel) body.getUserData()).getPowerup().action();
        }
    }


    public boolean isFinished() {
        return FINISHED;
    }

    public boolean isDEAD() {
        return DEAD;
    }

    public float getTime() {
        return time;
    }
}
