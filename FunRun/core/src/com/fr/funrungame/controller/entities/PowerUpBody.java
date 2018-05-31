package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;

public class PowerUpBody extends EntityBody {

    /**
     * Constructs a powerup body representing a model in a certain world.
     * @param world world
     * @param model model
     */
    public PowerUpBody(World world, EntityModel model) {
        super();
        createBody(world, model, BodyDef.BodyType.StaticBody);
        createFixture(model);
    }

    @Override
    protected void createFixture(EntityModel model) {
        FixtureDef fixturedef = getFixtureDef(model);
        fixturedef.isSensor = true;
        fixturedef.filter.categoryBits = TERRAIN_BODY;
        body.createFixture(fixturedef);

    }
}
