package com.fr.funrungame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.fr.funrungame.controller.entities.*;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class GameController implements ContactListener{

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * The arena width in meters.
     */
    public static final int GAME_WIDTH = 1080;

    /**
     * The arena height in meters.
     */
    public static final int GAME_HEIGHT = 720;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;

    /**
     * The physics world controlled by this controller.
     */
    private World world;

    /**
     * The players in this controller.
     */
    private final PlayerBody players[];

    /**
     * The platforms in this controller.
     */
    private List<PlatformBody> platformsBody;

    /**
     * The powerups in this controller.
     */
    private List<PowerUpBody> powerUps;

    /**
     * The enemies in this controller.
     */
    private List<EnemyBody> enemies;

    /**
     * The endline in this controller.
     */
    private EndLineBody endline;

    /**
     * The updated run time.
     */
    private float time;

    /**
     * The current map best time.
     */
    private static float best_time;

    /**
     * The set of actions for the ghost to follow
     */
    private static ArrayList<Float> actions;
    private static ArrayList<Float> actions_times;

    /**
     * The actions array index
     */
    private int index;

    /**
     * The player moves history
     */
    private ArrayList<Float> history;
    private ArrayList<Float> history_times;

    /**
     * Flag for server response
     */
    private static Networking network = new Networking();


    private GameController() {
        index = 0;
        history = new ArrayList<Float>();
        history_times = new ArrayList<Float>();
        world = new World(new Vector2(0, -9.8f), true);
        time = 0;


        players = new PlayerBody[2];
        players[0] = new PlayerBody(world, GameModel.getInstance().getPlayers().get(0));
        players[1] = new PlayerBody(world, GameModel.getInstance().getPlayers().get(1));

        platformsBody = new ArrayList<PlatformBody>();
        for(int i = 0; i < GameModel.getInstance().getPlatformsModel().size(); i++){
            platformsBody.add(new PlatformBody(world,GameModel.getInstance().getPlatformsModel().get(i)));
        }

        powerUps = new ArrayList<PowerUpBody>();
        for(int i = 0; i < GameModel.getInstance().getPowerUps().size(); i++){
            powerUps.add(new PowerUpBody(world,GameModel.getInstance().getPowerUps().get(i)));
        }
        enemies = new ArrayList<EnemyBody>();
        for(int i = 0; i < GameModel.getInstance().getEnemies().size(); i++){
            enemies.add(new EnemyBody(world,GameModel.getInstance().getEnemies().get(i)));
        }

        endline = new EndLineBody(world, GameModel.getInstance().getEndline());

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
        time += delta;
        GameModel.getInstance().update(delta);

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        ghostHandler(time);
        playerVerifications(delta);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
        }

        isRunFinished();
    }

    private void isRunFinished() {
        if(!((PlayerModel)players[0].getUserData()).isFinished()) return;
        sendToServer(GameModel.getInstance().getCurrentMap(), history_times, history, players[0].getTime());
        GameModel.getInstance().setFinished(true);

    }
    private void playerVerifications(float delta){

        for(PlayerBody p : players) {
            p.update(delta);
        }
    }

    private void ghostHandler(float time) {
        if(actions == null || actions_times == null || index == actions.size() || index == actions_times.size()) {
            double a = Math.random();
            if(a < 0.5) jump(players[1]);
            return;
        }

        if(actions_times.get(index) <= time)  {
            switch (Math.round(actions.get(index))) {
                case 1:
                    jump(players[1]);
                    break;
                case 2:
                    moveDown(players[1]);
                    break;
                case 3:
                    givePowerUp(players[1], 0);
                    break;
                case 4:
                    givePowerUp(players[1], 1);
                    break;
                case 5:
                    givePowerUp(players[1], 2);
                case 6:
                    usePowerUp(players[1]);
                default:
                    break;
            }
            index++;
        }
    }

    public void jump(PlayerBody p){
        if(p == players[0]) {
            updateHistory(1);
            p.jump(true);
        }
        else{
            p.jump(false);
        }
    }

    public void moveDown(PlayerBody p) {
        if(p == players[0]) {
            updateHistory(2);
        }
        p.moveDown();
    }

    public void usePowerUp(PlayerBody p){

        if(p == players[0]) {
            updateHistory(6);
        }
        p.usePowerUp();
    }

    private void givePowerUp(PlayerBody p, double option) {

        if(option == -1) option = Math.floor(Math.random() * Math.floor(3));
        switch ((int)option) {
            case 0:
                if(p == players[0])
                    ((PlayerModel) p.getUserData()).givePowerup(new SpeedPowerUpModel(), true);
                else
                    ((PlayerModel) p.getUserData()).givePowerup(new SpeedPowerUpModel(), false);
                break;
            case 1:
                if(p == players[0])
                    ((PlayerModel) p.getUserData()).givePowerup(new RocketPowerUpModel(), true);
                else
                    ((PlayerModel) p.getUserData()).givePowerup(new RocketPowerUpModel(), false);
                break;
            case 2:
                if(p == players[0])
                    ((PlayerModel) p.getUserData()).givePowerup(new ShieldPowerUpModel(), true);
                else
                    ((PlayerModel) p.getUserData()).givePowerup(new ShieldPowerUpModel(), false);
                break;
        }

        if(p == players[0]) {
            updateHistory((int)option + 3);
        }
    }

    public PlayerBody getPlayerBody() {
        return players[0];
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
            ((PlayerModel) bodyA.getUserData()).setJumping(false);
            ((PlayerModel) bodyA.getUserData()).setFalling(false);
        }

        else if (bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof PowerUpModel){
            if(players[0].getBody() == bodyA) {
                givePowerUp(players[0], -1);
            }

        }

        else if (bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof EnemyModel){
            if(players[0].getBody() == bodyA) players[0].die();
            else players[1].die();
        }

        else if (bodyA.getUserData() instanceof PlayerModel && bodyB.getUserData() instanceof EndLineModel){
            if(players[0].getBody() == bodyA) {
                players[0].setFinish();
            }
            else {
                players[1].setFinish();
            }

        }

    }

    private void updateHistory(int action) {
        //history.add(getTime());
        history_times.add(getTime());
        history.add((float) action);
    }

    public float getTime() {
        return time;
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

    public static void getFromServer() {
        network.get(GameModel.getInstance().getCurrentMap());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        best_time = network.getTime();
        actions = network.getActions();
        actions_times = network.getTimes();
    }

    private void sendToServer(int map, ArrayList<Float> history_times, ArrayList<Float> history, float time) {
        if(players[1].getTime() < players[0].getTime() ) return;

        network.send(map, history_times, history, time);
    }

    public static void reset() {
        if(actions_times != null)
            actions_times.clear();
        if(actions != null)
            actions.clear();
        instance = null;
    }

    public PlayerBody[] getPlayers() {
        return players;
    }
}
