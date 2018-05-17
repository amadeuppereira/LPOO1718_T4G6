package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(), model.getY());

        body = world.createBody(bodyDef);
        body.setUserData(model);

        float density = 0.5f, friction = 0.4f, restitution = 0.5f;
        int width = 84, height = 98;

//        createFixture(body, new float[]{
//                84,98,0
//        }, width, height, density, friction, restitution, PLAYER_BODY, (short) (PLAYER_BODY | ENEMY_BODY | POWER_UP_BODY));
    }
}
