package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super(world, model);
    }
}
