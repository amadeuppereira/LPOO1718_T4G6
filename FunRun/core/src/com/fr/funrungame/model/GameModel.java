package com.fr.funrungame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.fr.funrungame.model.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fr.funrungame.view.Screens.GameView.PIXEL_TO_METER;

public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    private List<PlayerModel> players;

    private List<EnemyModel> enemies;

    private List<PowerUpModel> powerUps;

    private List<PlatformModel> platformsModel;

    private EndLineModel endline;

    private TiledMap map;

    private int currentMap;

    private boolean flag = false;

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

    private GameModel(){
        players = new ArrayList<PlayerModel>();
        enemies = new ArrayList<EnemyModel>();
        powerUps = new ArrayList<PowerUpModel>();
        platformsModel = new ArrayList<PlatformModel>();
        currentMap = 1;
    }

    public void addEntities() {
        if (flag) return;
        flag = true;
        players.add(new PlayerModel(3, 8.1f));
        players.add(new PlayerModel(3, 8.1f));

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            platformsModel.add(new PlatformModel(PIXEL_TO_METER * (rect.getX() + rect.getWidth() / 2), PIXEL_TO_METER * (rect.getY() + rect.getHeight() / 2), (RectangleMapObject) object));
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
     * Removes a model from this game.
     *
     * @param model the model to be removed
     */
    public void remove(EntityModel model) {
        if (model instanceof PlayerModel) {
            players.remove(model);
        }
        if (model instanceof EnemyModel) {
            enemies.remove(model);
        }
        if (model instanceof PowerUpModel) {
            powerUps.remove(model);
        }
        if (model instanceof PlatformModel) {
            platformsModel.remove(model);
        }
    }

    public void update(float delta) {

    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map){
        this.map = map;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public List<EnemyModel> getEnemies() {
        return enemies;
    }

    public List<PowerUpModel> getPowerUps() {
        return powerUps;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public List<PlatformModel> getPlatformsModel() {
        return platformsModel;
    }

    public EndLineModel getEndline() {
        return endline;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public static void reset() {
        instance = null;
    }
}
