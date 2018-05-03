package com.fr.funrungame.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.fr.funrungame.model.entities.*;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    private List<PlayerModel> players;

    private List<EnemyModel> enemies;

    private List<PowerUpModel> powerUps;

    private TiledMap map;

    private int currentMap;


    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }

    private GameModel(){
        players = new ArrayList<PlayerModel>();
        players.add(new PlayerModel(10,48));
        enemies = new ArrayList<EnemyModel>();
        powerUps = new ArrayList<PowerUpModel>();

        currentMap = 1;
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
}
