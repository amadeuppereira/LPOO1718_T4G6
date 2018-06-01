package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;

/**
 * Class that handles the background and title for the MainMenu and MapSelect.
 */
public class MenuScreen extends ScreenAdapter {

    /**
     * The current game session.
     */
    protected FunRunGame game;

    /**
     * The width of the viewport in meters.
     */
    protected static final float VIEWPORT_WIDTH = 40;

    /**
     * The height of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * Menu screen viewport.
     */
    private Viewport viewport;

    /**
     * Menu screen stage.
     */
    protected Stage  stage;

    /**
     * Menu screen background image.
     */
    private Image backgroundImg;

    /**
     * Menu screen title.
     */
    private Image titleImg;

    /**
     * Menu Screen Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     * @param title The title image.
     * @param title_height The title height.
     * @param title_length The title length.
     */
    public MenuScreen(FunRunGame game, Image title, float title_height, float title_length) {
        this.game = game;
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport.apply();

        stage = new Stage(viewport, game.getBatch());

        backgroundImg = new Image(game.getAssetManager().get("background_menu.png", Texture.class));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        titleImg = title;
        titleImg.setSize(title_length, title_height);
        titleImg.setPosition(VIEWPORT_WIDTH / 2 - titleImg.getWidth() / 2, VIEWPORT_HEIGHT * 0.98f - titleImg.getHeight());
    }

    /**
     * Adds the background and title to the stage.
     */
    @Override
    public void show() {
        stage.addActor(backgroundImg);
        stage.addActor(titleImg);
    }

    /**
     * Renders the screen.
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Resizes the screen.
     *
     * @param width of the screen.
     * @param height of the screen.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Hides the screen.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
