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
     * Helper method to create a polygon fixture represented by a set of vertexes.
     * @param body The body the fixture is to be attached to.
     * @param vertexes The vertexes defining the fixture in pixels so it is
     *                 easier to get them from a bitmap image.
     * @param width The width of the bitmap the vertexes where extracted from.
     * @param height The height of the bitmap the vertexes where extracted from.
     * @param density The density of the fixture. How heavy it is in relation to its area.
     * @param friction The friction of the fixture. How slippery it is.
     * @param restitution The restitution of the fixture. How much it bounces.
     * @param category
     * @param mask
     */
    final void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {
        // Transform pixels into meters, center and invert the y-coordinate
        for (int i = 0; i < vertexes.length; i++) {
            if (i % 2 == 0) vertexes[i] -= width / 2;   // center the vertex x-coordinate
            if (i % 2 != 0) vertexes[i] -= height / 2;  // center the vertex y-coordinate

            if (i % 2 != 0) vertexes[i] *= -1;          // invert the y-coordinate

            vertexes[i] *= PIXEL_TO_METER;              // scale from pixel to meter
        }

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertexes);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygon;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.filter.categoryBits = category;
        fixtureDef.filter.maskBits = mask;

        body.createFixture(fixtureDef);

        polygon.dispose();
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

    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }
}
