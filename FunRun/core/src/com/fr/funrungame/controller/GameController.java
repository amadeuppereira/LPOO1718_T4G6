package com.fr.funrungame.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

abstract public class GameController implements ContactListener{

    /**
     * The arena width in meters.
     */
    public static final int GAME_WIDTH = 100;

    /**
     * The arena height in meters.
     */
    public static final int GAME_HEIGHT = 50;
}
