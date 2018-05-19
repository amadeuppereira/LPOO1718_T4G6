package com.fr.funrungame.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.fr.funrungame.controller.entities.EntityBody;
import com.fr.funrungame.controller.entities.PlatformBody;
import com.fr.funrungame.controller.entities.PlayerBody;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlatformModel;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.view.GameView;

import java.util.ArrayList;
import java.util.List;

import static com.fr.funrungame.view.GameView.PIXEL_TO_METER;

public class GameController implements ContactListener{

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * The arena width in meters.
     */
    public static final int GAME_WIDTH = 800;

    /**
     * The arena height in meters.
     */
    public static final int GAME_HEIGHT = 480;

    /**
     * The movement speed.
     */
    private static final float MOVEMENT_SPEED = 1;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    private final PlayerBody playerBody;

    private List<PlatformBody> platformsBody;


    private GameController() {
        world = new World(new Vector2(0, -9.8f), true);

        playerBody = new PlayerBody(world, GameModel.getInstance().getPlayers().get(0));

        platformsBody = new ArrayList<PlatformBody>();
        for(int i = 0; i < GameModel.getInstance().getPlatformsModel().size(); i++){
            platformsBody.add(new PlatformBody(world,GameModel.getInstance().getPlatformsModel().get(i), GameModel.getInstance().getPlatformsModel().get(i).getObject()));
        }

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

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        //to keep the player always moving forward
        if(playerBody.getBody().getLinearVelocity().x <= 5)
            playerBody.getBody().applyForceToCenter(15f,0, true);

        if(playerBody.getBody().getLinearVelocity().x == 0){
            ((PlayerModel) playerBody.getUserData()).setRunning(false);
        }
        else{
            ((PlayerModel) playerBody.getUserData()).setRunning(true);
        }

        if(playerBody.getBody().getLinearVelocity().y > 0){
            ((PlayerModel) playerBody.getUserData()).setJumping(true);
            ((PlayerModel) playerBody.getUserData()).setFalling(false);
        }
        else if(playerBody.getBody().getLinearVelocity().y < 0){
            ((PlayerModel) playerBody.getUserData()).setJumping(false);
            ((PlayerModel) playerBody.getUserData()).setFalling(true);
        }
//        else{
//            ((PlayerModel) playerBody.getUserData()).setJumping(false);
//            ((PlayerModel) playerBody.getUserData()).setFalling(false);
//        }



        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            //verifyBounds(body);
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
        if (body.getPosition().x < 1)
            body.setTransform(1, body.getPosition().y, body.getAngle());

        if (body.getPosition().y < 0)
            body.setTransform(body.getPosition().x, 0, body.getAngle());

        if (body.getPosition().x > GAME_WIDTH)
            body.setTransform(0, body.getPosition().y, body.getAngle());

        if (body.getPosition().y > GAME_HEIGHT)
            body.setTransform(body.getPosition().x, 0, body.getAngle());
    }

    public void jump(){
        if(playerBody.getBody().getLinearVelocity().y == 0)
            playerBody.jump();
    }

    public void moveDown() {
        playerBody.moveDown();
    }


    /**
     * A contact between two objects was detected
     *
     * @param contact the detected contact
     */
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if (bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof PlatformModel){
            ((PlayerModel) playerBody.getUserData()).setJumping(false);
            ((PlayerModel) playerBody.getUserData()).setFalling(false);
        }
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
