package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;

import static com.fr.funrungame.view.GameView.PIXEL_TO_METER;

public class PlayerBody extends EntityBody {

    private float RUN_FORCE = 12f;
    private float JUMP_FORCE = 5f;
    private float CLIMB_FORCE = 1.5f;
    private float DOWN_FORCE = -40f;
    private boolean DEAD = false;
    private boolean INVULNERABLE = false;

    private int death_timer = 0;
    private int invulnerable_timer = 0;

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
        //fixturedef.density = 1;
        body.createFixture(fixturedef);
    }

    public void update() {
        System.out.println("death_timer:        " + death_timer);
        System.out.println("invulnerable_timer: " + invulnerable_timer);
        if(DEAD) {
            death_timer--;
            if(death_timer == 0) DEAD = false;
        }

        if(INVULNERABLE) {
            invulnerable_timer--;
            if(invulnerable_timer == 0) INVULNERABLE = false;
        }
    }

    public void jump() {
        if(DEAD) return;
        body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
        ((PlayerModel) getUserData()).setJumping(true);
    }

    public void climb(){
        if(DEAD) return;
        body.applyLinearImpulse(new Vector2(0, CLIMB_FORCE), body.getWorldCenter(), true);
    }

    public void moveDown(){
        if(DEAD) return;
        body.applyForceToCenter(0, DOWN_FORCE, true);
    }

    public void run() {
        if(death_timer != 0) return;
        body.applyForceToCenter(RUN_FORCE,0, true);
    }

    public void stop() {
        body.setLinearVelocity(new Vector2(0,0));
    }

    public void die() {
        if(INVULNERABLE || DEAD) return;
        stop();
        death_timer = 100;
        invulnerable_timer = 150;
        INVULNERABLE = true;
        DEAD = true;
    }
}
