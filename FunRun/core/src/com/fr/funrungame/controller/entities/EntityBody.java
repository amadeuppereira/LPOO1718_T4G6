package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.model.entities.EntityModel;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

public abstract class EntityBody {
    /**
     * The constant that represents a player body
     */
    final static short PLAYER_BODY = 0x0002;

    /**
     * The constant that represents a terrain body
     */
    final static short TERRAIN_BODY = 0x0004;

    /**
     * The Box2D body that supports this body.
     */
    protected Body body;

    /**
     * Constructs a body representing a model in a certain world.
     */
    EntityBody() {}

    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY() {
        return body.getPosition().y;
    }

    /**
     * Wraps the getLinearVelocity().x from Box2D body class.
     *
     * @return the x axis velocity of this body
     */
    public float getVelX() {
        return body.getLinearVelocity().x;
    }

    /**
     * Wraps the getLinearVelocity().y from Box2D body class.
     *
     * @return the y axis velocity of this body
     */
    public float getVelY() {
        return body.getLinearVelocity().y;
    }

    /**
     * Applies a linear impulse in the center of the body
     *
     * @param x impulse x force
     * @param y impulse y force
     */
    public void applyLinearImpulse(float x, float y) {
        body.applyLinearImpulse(new Vector2(x,y), body.getWorldCenter(), true);
    }

    /**
     * Applies a force on the body
     *
     * @param x force
     * @param y force
     */
    public void applyForce(float x, float y){
        body.applyForce(new Vector2(x,y), body.getWorldCenter(), true);
    }

    /**
     * Get the Box2D body.
     *
     * @return Box2D body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }

    /**
     * Returns the default fixture of the body.
     *
     * @return the default fixture
     */
    protected FixtureDef getFixtureDef(EntityModel model) {
        PolygonShape shape = new PolygonShape();
        FixtureDef fixturedef = new FixtureDef();
        Rectangle rect = model.getObject().getRectangle();

        shape.setAsBox((rect.getWidth() / 2) * PIXEL_TO_METER, (rect.getHeight() / 2) * PIXEL_TO_METER);
        fixturedef.shape = shape;

        return fixturedef;
    }

    /**
     * Create a fixture with a given definition in the body
     *
     * @param model entity model
     */
    protected abstract void createFixture(EntityModel model);

    /**
     * Creates a body in the world
     *
     * @param world world
     * @param model entity model
     * @param bodyType body time
     */
    protected void createBody(World world, EntityModel model, BodyDef.BodyType bodyType) {
        BodyDef bodydef = new BodyDef();
        Rectangle rect = model.getObject().getRectangle();
        bodydef.type = bodyType;
        bodydef.position.set((rect.getX() + rect.getWidth() / 2) * PIXEL_TO_METER, (rect.getY() + rect.getHeight() / 2) * PIXEL_TO_METER);
        this.body = world.createBody(bodydef);

        body.setUserData(model);
    }
}
