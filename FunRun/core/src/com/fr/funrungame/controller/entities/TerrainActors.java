package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;


public class TerrainActors extends EntityBody {

    public enum Type {ENDLINE, POWERUP, ENEMY};
    private Type type;

    public TerrainActors(World world, EntityModel model, Type type) {
        super();
        this.type = type;
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

    public Type getType() {
        return type;
    }
}
