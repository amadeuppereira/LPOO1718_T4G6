package com.fr.funrungame.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.controller.entities.PlayerBody;
import com.fr.funrungame.model.GameModel;

import com.fr.funrungame.model.entities.*;
import org.junit.Test;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;
import static org.junit.Assert.*;

public class GameModelTest extends GameTest{

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
    public void testPlayerModel() {
        List<PlayerModel> p = GameModel.getInstance().getPlayers();
        for(int i = 0; i < p.size(); i++) {
            PlayerModel t = p.get(i);
            assertNull(t.getObject());
            assertNotNull(t.getX());
            assertNotNull(t.getY());

            assertNull(t.getPowerup());
            assertEquals(PlayerModel.State.DEFAULT, t.getState());
            assertEquals(PlayerModel.Boost.NONE, t.getBoost());
        }
    }

    @Test
    public void testRocketPowerupModel() {
        World world = new World(new Vector2(0,0), true);
        PlayerBody p = new PlayerBody(world, new PlayerModel(0, 0));
        RocketPowerUpModel powerup = new RocketPowerUpModel();
        assertEquals(0, p.getVelX(), 0.1);
        assertEquals(0, p.getVelY(), 0.1);
        assertEquals(0, powerup.update(1, p));
        powerup.activate(p);
        assertNotEquals(0, p.getVelX());
        assertNotEquals(0, p.getVelY());

        assertEquals(1, powerup.update(1, p));


        world.dispose();
    }

    @Test
    public void testSpeedPowerupModel() {
        World world = new World(new Vector2(0,0), true);
        PlayerBody p = new PlayerBody(world, new PlayerModel(0, 0));
        SpeedPowerUpModel powerup = new SpeedPowerUpModel();
        assertEquals(0, p.getVelX(), 0.1);
        assertEquals(0, p.getVelY(), 0.1);
        powerup.activate(p);
        assertNotEquals(0, p.getVelX());
        assertEquals(0, p.getVelY(), 0.1);

        assertEquals(0, powerup.update(0.5f, p), 0.1);
        assertEquals(0, powerup.update(3, p));
        assertEquals(1, powerup.update(1, p));

        world.dispose();

    }

    @Test
    public void testShieldPowerupModel() {
        World world = new World(new Vector2(0,0), true);
        PlayerBody p = new PlayerBody(world, new PlayerModel(0, 0));
        ShieldPowerUpModel powerup = new ShieldPowerUpModel();
        assertEquals(PlayerModel.Boost.NONE, ((PlayerModel)p.getUserData()).getBoost());
        powerup.activate(p);
        assertEquals(PlayerModel.Boost.SHIELD, ((PlayerModel)p.getUserData()).getBoost());

        assertEquals(0, powerup.update(1, p));
        assertEquals(0, powerup.update(3, p));
        assertEquals(1, powerup.update(1, p));

        world.dispose();

    }

    @Test
    public void testSetPosition() {
        PlayerModel p = new PlayerModel(0,0);
        assertEquals(0, p.getX(), 0.1);
        assertEquals(0, p.getY(), 0.1);
        p.setPosition(1,1);
        assertEquals(1, p.getX(), 0.1);
        assertEquals(1, p.getY(), 0.1);
    }

    @Test (timeout = 1000)
    public void testPlayerModelStatus() {
        PlayerModel p = new PlayerModel(0, 0);
        assertEquals(PlayerModel.State.DEFAULT, p.getState());

        boolean f1=false, f2=false, f3=false, f4=false, f5=false, f6=false;
        while (true) {
            if(f1 && f2 && f3 && f4 && f5 && f6) break;
            int n = random(1, 6);
            switch(n) {
                case 1:
                    p.setState(PlayerModel.State.DEFAULT);
                    assertEquals(PlayerModel.State.DEFAULT, p.getState());
                    f1 = true;
                    break;
                case 2:
                    p.setState(PlayerModel.State.RUNNING);
                    assertEquals(PlayerModel.State.RUNNING, p.getState());
                    f2 = true;
                    break;
                case 3:
                    p.setState(PlayerModel.State.JUMPING);
                    assertTrue(p.isJumping());
                    f3 = true;
                    break;
                case 4:
                    p.setState(PlayerModel.State.FALLING);
                    assertTrue(p.isFalling());
                    f4 = true;
                    break;
                case 5:
                    p.setState(PlayerModel.State.DEAD);
                    assertEquals(PlayerModel.State.DEAD, p.getState());
                    f5 = true;
                    break;
                case 6:
                    p.setState(PlayerModel.State.FINISH);
                    assertTrue(p.isFinished());
                    f6 = true;
                    break;
            }
        }
    }

    @Test (timeout = 1000)
    public void testPlayerBoostStatus() {
        PlayerModel p = new PlayerModel(0, 0);
        assertEquals(PlayerModel.Boost.NONE, p.getBoost());

        boolean f1 = false, f2 = false, f3 = false;
        while (true) {
            if (f1 && f2 && f3) break;
            int n = random(1, 3);
            switch (n) {
                case 1:
                    p.setBoost(PlayerModel.Boost.NONE);
                    assertEquals(PlayerModel.Boost.NONE, p.getBoost());
                    f1 = true;
                    break;
                case 2:
                    p.setBoost(PlayerModel.Boost.SHIELD);
                    assertEquals(PlayerModel.Boost.SHIELD, p.getBoost());
                    f2 = true;
                    break;
                case 3:
                    p.setBoost(PlayerModel.Boost.INVULNERABLE);
                    assertEquals(PlayerModel.Boost.INVULNERABLE, p.getBoost());
                    f3 = true;
                    break;
            }
        }

        p.setBoost(PlayerModel.Boost.SHIELD);
        p.removeBoost();
        assertEquals(PlayerModel.Boost.NONE, p.getBoost());
    }

    @Test (timeout = 1000)
    public void testGivePowerup() {
        PlayerModel p = new PlayerModel(0, 0);

        assertNull(p.getPowerup());

        p.givePowerup(new RocketPowerUpModel(), true);
        assertTrue(p.getPowerup() instanceof RocketPowerUpModel);
        p.givePowerup(new SpeedPowerUpModel(), true);
        assertTrue(p.getPowerup() instanceof RocketPowerUpModel);

        p.removePowerup();
        assertNull(p.getPowerup());

        p.givePowerup(new ShieldPowerUpModel(), true);
        assertTrue(p.getPowerup() instanceof ShieldPowerUpModel);
    }

    @Test
    public void testDefaultPowerup() {
        World world = new World(new Vector2(0,0), true);
        PlayerBody p = new PlayerBody(world, new PlayerModel(0, 0));
        PowerUpModel powerup = new PowerUpModel();

        powerup.activate(p);
        assertEquals(0, powerup.update(1f, p));
        powerup.action(p);
        
        world.dispose();

    }





}
