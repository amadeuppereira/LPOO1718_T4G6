package com.fr.funrungame.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameControllerTest extends GameTest {

    TiledMap map = new TmxMapLoader().load("maps/test_map.tmx");

    @Test
    public void testControllerCreation() {
        GameModel.getInstance().setMap(map);
        GameModel.getInstance().addEntities();

        GameController.getInstance();

        assertNotNull(GameController.getInstance().getPlayers());
        assertNotNull(GameController.getInstance().getEndline());
        assertNotNull(GameController.getInstance().getEnemies());
        assertNotNull(GameController.getInstance().getPlatformsBody());
        assertNotNull(GameController.getInstance().getPowerUps());
    }

    @Test
    public void testNoActionUpdate() {
        GameModel.getInstance().setMap(map);
        GameModel.getInstance().addEntities();

        float[] pos = {GameController.getInstance().getPlayerBody().getX(), GameController.getInstance().getPlayerBody().getY()};

        GameController.getInstance().update(5);

        assertTrue(GameController.getInstance().getPlayerBody().getX() > pos[0]);
        assertTrue(GameController.getInstance().getPlayerBody().getX() == pos[1]);
    }
}
