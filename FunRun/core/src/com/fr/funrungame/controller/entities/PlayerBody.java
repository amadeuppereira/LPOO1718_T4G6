package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;

import static com.fr.funrungame.view.GameView.PIXEL_TO_METER;

public class PlayerBody extends EntityBody {

    public PlayerBody(World world, EntityModel model) {
        super();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        System.out.println(model.getX());
        //bodyDef.position.set(model.getX() / PIXEL_TO_METER, model.getY() / PIXEL_TO_METER);
        bodyDef.position.set(model.getX(), model.getY());

        body = world.createBody(bodyDef);
        body.setUserData(model);

        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(53/2, 84/2, new Vector2(53/2,84/2), 0);
        shape.setAsBox(53/2 * PIXEL_TO_METER, 84/2 * PIXEL_TO_METER);
        FixtureDef fixturedef = new FixtureDef();
        fixturedef.shape = shape;
        body.createFixture(fixturedef);
    }

    public void jump() {
            applyForce(0, 100);
//        playerBody.setTransform(playerBody.getX(), playerBody.getY()+(MOVEMENT_SPEED * delta));
            ((PlayerModel) getUserData()).setJumping(true);
    }
}
