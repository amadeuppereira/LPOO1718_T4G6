package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;

public class CountdownScreen extends GameView {

    private final int TIME_BETWEEN_CHANGES = 50;
    private final float NUMBER_WIDTH = 50;
    private final float NUMBER_HEIGHT = 50;

    private Texture number;
    private int n;

    private float xi, yi;

    private int timer = TIME_BETWEEN_CHANGES;

    public CountdownScreen(FunRunGame game) {
        super(game);

        n = 3;
        number = game.getAssetManager().get("3.png", Texture.class);
        xi = Gdx.graphics.getWidth() / 2 - (NUMBER_WIDTH / 2);
        yi = Gdx.graphics.getHeight() * 0.999999f- (NUMBER_HEIGHT / 2);
    }

    @Override
    public void render(float delta) {
        cameraHandler();
        // Clear the screen
        update(delta);

        Gdx.gl.glClearColor( 0/255f, 0/255f, 0/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        float x = camera.position.x - camera.viewportWidth * camera.zoom;
        float y = camera.position.y - camera.viewportHeight * camera.zoom;

        float width = camera.viewportWidth * camera.zoom * 2;
        float height = camera.viewportHeight * camera.zoom * 2;

        mapRenderer.setView(camera.combined, x, y, width, height);
        mapRenderer.render();

        game.getBatch().begin();
        drawEntities();
        game.getBatch().draw(number,  xi, yi, NUMBER_WIDTH, NUMBER_HEIGHT);
        game.getBatch().end();

    }

    private void update(float delta) {
        timer -= delta;
        if(timer <= 0) {
            if(n == 3) {
                timer = TIME_BETWEEN_CHANGES;
                number = game.getAssetManager().get("2.jpg", Texture.class);
                n = 2;
            }
            else if(n == 2) {
                timer = TIME_BETWEEN_CHANGES;
                number = game.getAssetManager().get("1.jpg", Texture.class);
                n = 1;
            }
            else {
                dispose();
                game.setScreen(new GameView(game));
            }
        }
    }
}
