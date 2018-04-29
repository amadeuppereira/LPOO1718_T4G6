package com.fr.funrungame.model;

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

    private List<GameMap> maps;

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
        enemies = new ArrayList<EnemyModel>();
        powerUps = new ArrayList<PowerUpModel>();

        //readMaps() - need to implement a function that reads the maps from a file
        maps = new ArrayList<GameMap>();
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



    public List<PlayerModel> getPlayers() {
        return players;
    }

    public List<EnemyModel> getEnemies() {
        return enemies;
    }

    public List<PowerUpModel> getPowerUps() {
        return powerUps;
    }

    public List<GameMap> getMaps() {
        return maps;
    }

    public int getCurrentMap() {
        return currentMap;
    }
}
