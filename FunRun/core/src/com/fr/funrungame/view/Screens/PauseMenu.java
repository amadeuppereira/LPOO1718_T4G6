package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;

/**
 * Classe that pauses the current game. It can be resumed or exited.
 */
public class PauseMenu extends ScreenAdapter {

    /**
     * The width of the viewport in meters.
     */
    private static final float VIEWPORT_WIDTH = 40;

    /**
     * The height of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * Constant representing the Container Width.
     */
    private static final float CONTAINER_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing the Container Height.
     */
    private static final float CONTAINER_HEIGHT = VIEWPORT_HEIGHT / 2;

    /**
     * Constant representing all the Buttons' Width.
     */
    private static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 5;

    /**
     * Constant representing all the Buttons' Height.
     */
    private static final float BUTTON_HEIGHT = VIEWPORT_HEIGHT / 12;

    /**
     * The current game session.
     */
    private FunRunGame game;

    /**
     * Pause Menu screen viewport.
     */
    private Viewport viewport;

    /**
     * Pause Menu screen stage.
     */
    private Stage stage;

    /**
     * Pause Menu background image.
     */
    private Image backgroundImg;

    /**
     * Table with the container.
     */
    private Table container_table;

    /**
     * Table with the buttons.
     */
    private Table buttons_table;

    /**
     * Pause Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public PauseMenu(FunRunGame game){
        this.game = game;
        this.viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.viewport.apply();

        stage = new Stage(this.viewport, game.getBatch());

        backgroundImg = new Image(new Texture(Gdx.files.local("screenshot_background.png")));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        createContainer();
        createButtons();
    }

    /**
     * Creates the container table.
     */
    private void createContainer() {
        container_table = new Table();

        Image container = new Image(game.getAssetManager().get("pause_menu.png", Texture.class));
        container_table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT);

        container_table.setFillParent(true);
    }

    /**
     * Creates the buttons table.
     */
    private void createButtons() {
        buttons_table = new Table();
        buttons_table.padTop(6);

        Image leave = new Image(game.getAssetManager().get("leave_button.png", Texture.class));
        leave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                GameController.reset();
                GameModel.reset();
                game.setScreen(new MainMenu(game));
            }
        });
        buttons_table.add(leave).size(BUTTON_WIDTH, BUTTON_HEIGHT).padRight(1f);

        Image stay = new Image(game.getAssetManager().get("stay_button.png", Texture.class));
        stay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameView(game));
            }
        });
        buttons_table.add(stay).size(BUTTON_WIDTH, BUTTON_HEIGHT).padLeft(0.5f);

        buttons_table.setFillParent(true);
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
     * Disposes the screen.
     */
    @Override
    public void dispose() {
        super.dispose();
        Gdx.files.local("screenshot_background.png").delete();
        Gdx.input.setInputProcessor(null);
    }
}
