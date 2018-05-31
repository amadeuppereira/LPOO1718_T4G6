package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.model.entities.EntityModel;


public class EndLineBody extends EntityBody {

    /**
     * Constructs an endline body representing a model in a certain world.
     * @param world world
     * @param model model
     */
    public EndLineBody(World world, EntityModel model) {
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
