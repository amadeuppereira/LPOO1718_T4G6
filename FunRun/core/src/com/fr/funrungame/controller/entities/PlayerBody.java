package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;

import java.util.ArrayList;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

public class PlayerBody extends EntityBody {

    private float RUN_FORCE = 12f;
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

    private ArrayList history;

    public PlayerBody(World world, EntityModel model) {
        super();
        history = new ArrayList();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());


        body = world.createBody(bodyDef);
        body.setUserData(model);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(55/2 * PIXEL_TO_METER, 84/2 * PIXEL_TO_METER);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        body.createFixture(fixturedef);
    }

    public void update(float delta) {
        history.add(0);

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

    public void jump(int f) {
        history.add(1);
        if(DEAD || FINISHED) return;
        if(f == 0) {//jump
            body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
            ((PlayerModel) getUserData()).setJumping(true);
        }
        else {
            body.applyLinearImpulse(new Vector2(0, CLIMB_FORCE), body.getWorldCenter(), true);
        }
    }

    public void moveDown(){
        history.add(2);
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
        FINISHED = true;
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
        if(FINISHED){
            stop();
            return;
        }
        if(body.getLinearVelocity().x <= 8 && body.getLinearVelocity().y <= 10)
            body.applyLinearImpulse(new Vector2(1,2), body.getWorldCenter(),true);
    }

    public void shieldPowerUp(boolean shield){
        SHIELD = shield;
        ((PlayerModel)getUserData()).setShield(shield);
    }


    public boolean isFINISHED() {
        return FINISHED;
    }

    public boolean isDEAD() {
        return DEAD;
    }
}
