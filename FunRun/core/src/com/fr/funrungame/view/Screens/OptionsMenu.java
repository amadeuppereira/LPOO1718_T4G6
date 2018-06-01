package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;

public abstract class OptionsMenu extends ScreenAdapter {

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
     * Constant representing the Container Width.
     */
    protected static final float CONTAINER_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing the Container Height.
     */
    protected static final float CONTAINER_HEIGHT = VIEWPORT_HEIGHT / 2;

    /**
     * Constant representing all the Buttons' Width.
     */
    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 5;

    /**
     * Constant representing all the Buttons' Height.
     */
    protected static final float BUTTON_HEIGHT = VIEWPORT_HEIGHT / 12;

    /**
     * The current game session.
     */
    protected FunRunGame game;

    /**
     * Finish Menu screen viewport.
     */
    protected Viewport viewport;

    /**
     * Finish Menu screen stage.
     */
    protected Stage stage;

    /**
     * Finish Menu screen background image.
     */
    protected Image backgroundImg;

    /**
     * Table with the container.
     */
    protected Table container_table;

    /**
     * Table with the buttons.
     */
    protected Table buttons_table;

    /**
     * Creates an options menu object.
     */
    public OptionsMenu(FunRunGame game) {
        this.game = game;
        this.viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.viewport.apply();

        stage = new Stage(this.viewport, game.getBatch());

        backgroundImg = new Image(new Texture(Gdx.files.local("screenshot_background.png")));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());
    }

    /**
     * Creates the container table.
     */
    protected abstract void createContainer();

    /**
     * Creates the buttons table.
     */
    protected abstract void createButtons();

    /**
     * Exits the option menu.
     */
    protected void close() {
        dispose();
        GameController.reset();
        GameModel.reset();
        game.setScreen(new MainMenu(game));
    }

    /**
     * Disposes the screen.
     */
    @Override
    public void dispose() {
        super.dispose();
        Gdx.files.local("screenshot_background.png").delete();
        Gdx.input.setInputProcessor(null);
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
     * Renders the screen.
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Adds the background and all the tables to the stage.
     */
    @Override
    public void show() {
        stage.addActor(backgroundImg);
        stage.addActor(container_table);
        stage.addActor(buttons_table);

        Gdx.input.setInputProcessor(stage);
    }


}
