package com.fr.funrungame.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.EntityModel;

public class GameController implements ContactListener{

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * The arena width in meters.
     */
    public static final int GAME_WIDTH = 1980;

    /**
     * The arena height in meters.
     */
    public static final int GAME_HEIGHT = 1080;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;


    private GameController() {
        world = new World(new Vector2(0, 0), true);


        world.setContactListener(this);
    }

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
        GameModel.getInstance().update(delta);
//
//        timeToNextShoot -= delta;
//
//        float frameTime = Math.min(delta, 0.25f);
//        accumulator += frameTime;
//        while (accumulator >= 1/60f) {
//            world.step(1/60f, 6, 2);
//            accumulator -= 1/60f;
//        }
//
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            verifyBounds(body);
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }
    }

    /**
     * Verifies if the body is inside the arena bounds and if not
     * wraps it around to the other side.
     *
     * @param body The body to be verified.
     */
    private void verifyBounds(Body body) {
//        if (body.getPosition().x < 0)
//            body.setTransform(ARENA_WIDTH, body.getPosition().y, body.getAngle());
//
//        if (body.getPosition().y < 0)
//            body.setTransform(body.getPosition().x, ARENA_HEIGHT, body.getAngle());
//
//        if (body.getPosition().x > ARENA_WIDTH)
//            body.setTransform(0, body.getPosition().y, body.getAngle());
//
//        if (body.getPosition().y > ARENA_HEIGHT)
//            body.setTransform(body.getPosition().x, 0, body.getAngle());
    }

    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
