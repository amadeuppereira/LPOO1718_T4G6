package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.model.entities.EntityModel;

import static com.fr.funrungame.view.GameView.PIXEL_TO_METER;

public abstract class EntityBody {

    final static short PLAYER_BODY = 0x0001;

    final static short ENEMY_BODY = 0x0002;

    final static short POWER_UP_BODY = 0x0003;

    /**
     * The Box2D body that supports this body.
     */
    protected Body body;

    /**
     * Constructs a body representing a model in a certain world.
     */
    EntityBody() {
    }

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
     * Wraps the setTransform method from the Box2D body class.
     *
     * @param x the new x-coordinate for this body
     * @param y the new y-coordinate for this body
     */
    public void setTransform(float x, float y) {
        body.setTransform(x, y,0);
    }

    public void applyForce(float x, float y) {
        body.applyForceToCenter(x, y, true);
    }



    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }
}
