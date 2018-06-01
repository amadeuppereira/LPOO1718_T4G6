package com.fr.funrungame.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.fr.funrungame.model.GameModel;

import com.fr.funrungame.model.entities.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameLogicTest extends GameTest{

    @Test
    public void mapLoaderTest() {
        assertNull(GameModel.getInstance().getMap());
        TiledMap map = new TmxMapLoader().load("maps/test_map.tmx");
        GameModel.getInstance().setMap(map);
        assertNotNull(GameModel.getInstance().getMap());
        assertFalse(GameModel.getInstance().isFinished());

        GameModel.getInstance().addEntities();

        assertNotNull(GameModel.getInstance().getPlayers());
        assertNotNull(GameModel.getInstance().getEndline());
        assertNotNull(GameModel.getInstance().getEnemies());
        assertNotNull(GameModel.getInstance().getPlatforms());
        assertNotNull(GameModel.getInstance().getPowerUps());
    }

    @Test
    public void testPlatformModel() {
        List<PlatformModel> p = GameModel.getInstance().getPlatforms();
        for(int i = 0; i < p.size(); i++) {
            PlatformModel t = p.get(i);
            assertNotNull(t.getObject());
            assertNotNull(t.getX());
            assertNotNull(t.getY());
        }

    }

    @Test
    public void testEndlineModel() {
        EndLineModel p = GameModel.getInstance().getEndline();
        assertNotNull(p.getObject());
        assertNotNull(p.getX());
        assertNotNull(p.getY());
    }

    @Test
    public void testEnemiesModel() {
        List<EnemyModel> p = GameModel.getInstance().getEnemies();
        for(int i = 0; i < p.size(); i++) {
            EnemyModel t = p.get(i);
            assertNotNull(t.getObject());
            assertNotNull(t.getX());
            assertNotNull(t.getY());
        }

    }

    @Test
    public void testPowerupModel() {
        List<PowerUpModel> p = GameModel.getInstance().getPowerUps();
        for(int i = 0; i < p.size(); i++) {
            PowerUpModel t = p.get(i);
            assertNotNull(t.getObject());
            assertNotNull(t.getX());
            assertNotNull(t.getY());
        }
    }

    @Test
    public void testPlayerBody() {
        List<PlayerModel> p = GameModel.getInstance().getPlayers();
        for(int i = 0; i < p.size(); i++) {
            PlayerModel t = p.get(i);
            assertNull(t.getObject());
            assertNotNull(t.getX());
            assertNotNull(t.getY());

            assertNull(t.getPowerup());
        }
    }





}
