package com.fr.funrungame.test;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.*;
import org.junit.Test;

import static com.badlogic.gdx.math.MathUtils.random;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GameControllerTest extends GameTest {

    TiledMap map = new TmxMapLoader().load("maps/test_map.tmx");

    private void stepGame(float time) {
        float timer = time;

        while(timer > 0) {
            timer -= 0.1;
            GameController.getInstance().update(0.1f);
        }
    }

    private void createController() {
        GameModel.getInstance().setMap(map);
        GameModel.getInstance().addEntities();

        GameController.getInstance().update(1);
    }

    @Test
    public void testControllerCreation() {
        createController();

        assertNotNull(GameController.getInstance().getPlayers());
        assertNotNull(GameController.getInstance().getEndline());
        assertNotNull(GameController.getInstance().getEnemies());
        assertNotNull(GameController.getInstance().getPlatformsBody());
        assertNotNull(GameController.getInstance().getPowerUps());
    }

    @Test
    public void testNoAction() {
        createController();

        float[] pos = {GameController.getInstance().getPlayerBody().getX(), GameController.getInstance().getPlayerBody().getY()};

        stepGame(3);

        assertTrue(GameController.getInstance().getPlayerBody().getX() > pos[0]);
        assertTrue(GameController.getInstance().getPlayerBody().getY() <= pos[1]);
    }

    @Test
    public void testJump() {
        createController();

        float init_pos = GameController.getInstance().getPlayerBody().getY();

        GameController.getInstance().jump(GameController.getInstance().getPlayerBody());

        stepGame(0.2f);
        float speed1 = GameController.getInstance().getPlayerBody().getVelY();
        assertTrue(GameController.getInstance().getPlayerBody().getY() > init_pos);

        float pos =GameController.getInstance().getPlayerBody().getY();
        stepGame(0.2f);
        float speed2 = GameController.getInstance().getPlayerBody().getVelY();
        assertTrue(GameController.getInstance().getPlayerBody().getY() > pos);
        assertTrue(speed2 < speed1);

        stepGame(1);
        assertTrue(GameController.getInstance().getPlayerBody().getY() < pos);

        stepGame(3);

        assertEquals(GameController.getInstance().getPlayerBody().getY(), init_pos, 0.1);
    }

    @Test
    public void testMoveDown() {
        createController();

        //default jump test
        float[] pos = {GameController.getInstance().getPlayerBody().getX(), GameController.getInstance().getPlayerBody().getY()};
        GameController.getInstance().jump(GameController.getInstance().getPlayerBody());
        stepGame(0.1f);
        while(((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).isJumping() ||
                ((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).isFalling()) {
            stepGame(0.1f);
        }
        float[] pos1 = {GameController.getInstance().getPlayerBody().getX(), GameController.getInstance().getPlayerBody().getY()};
        float dx = pos1[0] - pos[0];

        stepGame(0.1f);

        //jump test with move down
        GameController.getInstance().jump(GameController.getInstance().getPlayerBody());
        stepGame(0.1f);
        GameController.getInstance().moveDown(GameController.getInstance().getPlayerBody());
        while(((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).isJumping() ||
                ((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).isFalling()) {
            stepGame(0.1f);
        }
        float dx1 = GameController.getInstance().getPlayerBody().getX() - pos1[0];

        assertTrue(dx1 > dx);
    }

    @Test (timeout = 1000)
    public void testGiveRandomPowerup() {
        createController();

        assertNull(((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).getPowerup());

        boolean f1 = false, f2 = false, f3 = false;
        while (!f1 && !f2 && !f3) {
            GameController.getInstance().givePowerUp(GameController.getInstance().getPlayerBody(), -1);
            PowerUpModel p = ((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).getPowerup();
            if(p instanceof RocketPowerUpModel)
                f1 = true;
            else if(p instanceof SpeedPowerUpModel)
                f2 = true;
            else if(p instanceof ShieldPowerUpModel)
                f3 = true;

            ((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).removePowerup();
        }

    }

    @Test
    public void testPlayerDead(){
        createController();

        assertEquals(false, GameController.getInstance().getPlayerBody().isDead());
        GameController.getInstance().getPlayerBody().die();
        assertEquals(false, GameController.getInstance().getPlayerBody().isDead());
    }

    @Test
    public void testPlayerFinish(){
        createController();

        assertEquals(false, GameController.getInstance().getPlayerBody().isFinished());
        GameController.getInstance().getPlayerBody().setFinish();
        assertEquals(true, GameController.getInstance().getPlayerBody().isFinished());
    }

    @Test
    public void testPlayerUsedPowerUp(){
        createController();

        GameController.getInstance().givePowerUp(GameController.getInstance().getPlayerBody(), -1);
        GameController.getInstance().getPlayerBody().usePowerUp();
        assertNotNull(((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).getPowerup());
        stepGame(3.1f);
        assertNull(((PlayerModel)GameController.getInstance().getPlayerBody().getUserData()).getPowerup());
    }

}
