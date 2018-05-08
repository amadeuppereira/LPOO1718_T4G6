package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super(world, model);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 84, height = 98;

        // Left winglet
//        createFixture(body, new float[]{
//                12,28, 15,28, 19,32, 19,42, 13,43
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
//
//        // Right winglet
//        createFixture(body, new float[]{
//                61,28, 58,28, 55,32, 55,42, 60,43
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
//
//        // Left wing
//        createFixture(body, new float[]{
//                19,32, 19,42, 31,46, 31,25
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
//
//        // Right wing
//        createFixture(body, new float[]{
//                55,32, 55,42, 43,46, 43,25
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
//
//        // Body
//        createFixture(body, new float[]{
//                32,12, 31,46, 34,51, 40,51, 43,46, 41,12
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
    }
}
