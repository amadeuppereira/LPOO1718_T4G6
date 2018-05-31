package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.fr.funrungame.FunRunGame;

public class LoadingScreen extends ScreenAdapter {

    public final static float PIXEL_TO_METER = 0.015f;
    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_WIDTH = 40;

    /**
     * The height of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());


    private FunRunGame game;
    private float time;
    private int elapsed;
    Texture backgroundImg;
    BitmapFont text;


    public LoadingScreen(FunRunGame game) {
        this.game = game;
        elapsed = 0;
        time = 0;


        text = new BitmapFont();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = new Texture(Gdx.files.local("loading_screen.jpg"));
        //backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        time += delta;
        if(time >= 1) {
            elapsed++;
            time = 0;
        }

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        text.getData().setScale(2);

        game.getBatch().begin();
        game.getBatch().draw(backgroundImg, 0, 0, backgroundImg.getWidth(), backgroundImg.getHeight());
        text.draw(game.getBatch(), "Time passed: " + elapsed, 50, 50);
        game.getBatch().end();



    }
}

