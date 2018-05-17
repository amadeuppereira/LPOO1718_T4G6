package com.fr.funrungame.controller.entities;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fr.funrungame.model.entities.EntityModel;

import static com.fr.funrungame.view.GameView.PIXEL_TO_METER;

public class PlatformBody extends EntityBody {

    public PlatformBody(World world, EntityModel model, RectangleMapObject object) {
        super();

        BodyDef bodydef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixturedef = new FixtureDef();

        Rectangle rect = object.getRectangle();

        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set((rect.getX() + rect.getWidth() / 2) * PIXEL_TO_METER, (rect.getY() + rect.getHeight() / 2) * PIXEL_TO_METER);

        this.body = world.createBody(bodydef);

        shape.setAsBox((rect.getWidth() / 2) * PIXEL_TO_METER, (rect.getHeight() / 2) * PIXEL_TO_METER);
        fixturedef.shape = shape;
        body.createFixture(fixturedef);
        body.setUserData(model);
    }
}
