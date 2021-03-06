package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.model.entities.EntityModel;

/**
 * A concrete representation of an EntityBody
 * representing the map platform.
 */
public class PlatformBody extends EntityBody {

    /**
     * Constructs a platform body representing a model in a certain world.
     *
     * @param world world
     * @param model model
     */
    public PlatformBody(World world, EntityModel model) {
        super();
        createBody(world, model, BodyDef.BodyType.StaticBody);
        createFixture(model);
    }

    /**
     * Create a fixture with a given definition in the body
     *
     * @param model entity model
     */
    @Override
    protected void createFixture(EntityModel model) {
        FixtureDef fixturedef = getFixtureDef(model);
        fixturedef.filter.categoryBits = TERRAIN_BODY;
        body.createFixture(fixturedef);
    }
}
