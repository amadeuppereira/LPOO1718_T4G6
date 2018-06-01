package com.fr.funrungame.model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.fr.funrungame.model.entities.*;

import java.util.ArrayList;
import java.util.List;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

/**
 * A model representing a game.
 */
public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    /**
     * ArrayList with the player and ghost
     */
    private List<PlayerModel> players;

    /**
     * ArrayList with the enemies
     */
    private List<EnemyModel> enemies;

    /**
     * ArrayList with the power ups
     */
    private List<PowerUpModel> powerUps;

    /**
     * ArrayList with the map platforms
     */
    private List<PlatformModel> platforms;

    /**
     * Object representing the end of the map
     */
    private EndLineModel endline;

    /**
     * Current tiled map
     */
    private TiledMap map;

    /**
     * Current game map number
     */
    private int currentMap;

    /**
     * Flag that prevents the models to be load more than once
     */
    private boolean flag = false;

    /**
     * Is the game finished
     */
    private boolean finished = false;

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    /**
     * Initializes all the models ArrayList and sets the current map 1
     */
    private GameModel(){
        players = new ArrayList<PlayerModel>();
        enemies = new ArrayList<EnemyModel>();
        powerUps = new ArrayList<PowerUpModel>();
        platforms = new ArrayList<PlatformModel>();
        currentMap = 1;
    }

    /**
     * Reads the objects from the tiled map and stores them in their proper ArrayLists
     */
    public void addEntities() {
        if (flag) return;
        flag = true;
        players.add(new PlayerModel(3, 8.1f));
        players.add(new PlayerModel(3, 8.1f));

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            platforms.add(new PlatformModel(PIXEL_TO_METER * (rect.getX() + rect.getWidth() / 2), PIXEL_TO_METER * (rect.getY() + rect.getHeight() / 2), (RectangleMapObject) object));
        }

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            powerUps.add(new PowerUpModel(PIXEL_TO_METER * (rect.getX() + rect.getWidth() / 2), PIXEL_TO_METER * (rect.getY() + rect.getHeight() / 2), (RectangleMapObject) object));
        }

        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            enemies.add(new EnemyModel(PIXEL_TO_METER * (rect.getX() + rect.getWidth() / 2), PIXEL_TO_METER * (rect.getY() + rect.getHeight() / 2), (RectangleMapObject) object));
        }

        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            endline = new EndLineModel(PIXEL_TO_METER * (rect.getX() + rect.getWidth() / 2), PIXEL_TO_METER * (rect.getY() + rect.getHeight() / 2), (RectangleMapObject) object);
        }
    }

    /**
     * Returns the current tiled map
     *
     * @return map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Sets a new tiled map
     *
     * @param map sets as the current tiled map
     */
    public void setMap(TiledMap map){
        this.map = map;
    }

    /**
     * Returns the player and ghost ArrayList
     *
     * @return players
     */
    public List<PlayerModel> getPlayers() {
        return players;
    }

    /**
     * Returns the enemies ArrayList
     *
     * @return enemies
     */
    public List<EnemyModel> getEnemies() {
        return enemies;
    }

    /**
     * Returns the power ups ArrayList
     *
     * @return powerUps
     */
    public List<PowerUpModel> getPowerUps() {
        return powerUps;
    }

    /**
     * Returns the platforms ArrayList
     *
     * @return platforms
     */
    public List<PlatformModel> getPlatforms() {
        return platforms;
    }

    /**
     * Returns the endline
     *
     * @return endline
     */
    public EndLineModel getEndline() {
        return endline;
    }

    /**
     * Returns the number of the current map
     *
     * @return currentMap
     */
    public int getCurrentMap() {
        return currentMap;
    }

    /**
     * Sets a new current map number
     *
     * @param currentMap nem current map
     */
    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    /**
     * Returns if the game is finished
     *
     * @return finish
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the game if it is finished or not
     *
     * @param finished new state of the game
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Deletes the GameModel instance
     */
    public static void reset() {
        instance = null;
    }
}
