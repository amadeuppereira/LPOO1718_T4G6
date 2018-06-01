package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;

/**
 * Class that loads the last best play of the current map from the server.
 */
public class LoadingScreen extends ScreenAdapter {
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
     * Constant representing all the Buttons' Width.
     */
    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 5;

    /**
     * Constant representing all the Buttons' Height.
     */
    protected static final float BUTTON_HEIGHT = VIEWPORT_HEIGHT / 15;

    /**
     * Constant representing the Container Width.
     */
    protected static final float CONTAINER_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing the Container Height.
     */
    protected static final float CONTAINER_HEIGHT = VIEWPORT_HEIGHT / 3;

    /**
     * The current game session.
     */
    private FunRunGame game;

    /**
     * Loading screen viewport.
     */
    private Viewport viewport;

    /**
     * Loading screen stage.
     */
    private Stage stage;

    /**
     * Loading screen background image.
     */
    private Image backgroundImg;

    /**
     * Time counter.
     */
    private float time;

    /**
     * The table containing the elements, that will be added to the stage.
     */
    private Table table;

    /**
     * Flag that tells if the server was already accessed
     */
    private boolean flag;

    private Table buttons_table;
    private Table container_table;

    /**
     * Loading Screen Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public LoadingScreen(FunRunGame game) {
        this.game = game;
        this.viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.viewport.apply();

        stage = new Stage(this.viewport, game.getBatch());

        time = 0;

        loadBackground();
        createTable();
    }

    /**
     * Loads and sets the backgroud.
     */
    private void loadBackground() {
        backgroundImg = new Image (game.getAssetManager().get("background_menu.png", Texture.class));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());
    }

    /**
     * Creates the table.
     */
    private void createTable() {
        table = new Table();

        Group group = new Group();

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        Label text = new Label("LOADING...", font);

        group.addActor(text);
        group.setScale(0.07f);

        table.add(group).padLeft(5).padBottom(5);
    }

    private void createContainer() {
        container_table = new Table();

        Image container = new Image(game.getAssetManager().get("failed_connect_server.png", Texture.class));
        container_table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT);

        container_table.setFillParent(true);
    }

    private void createButtons() {
        buttons_table = new Table();
        buttons_table.padTop(2);

        Image leave = new Image(game.getAssetManager().get("try_again.png", Texture.class));
        leave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game));
            }
        });
        buttons_table.add(leave).size(BUTTON_WIDTH, BUTTON_HEIGHT).padRight(1f);

        Image stay = new Image(game.getAssetManager().get("continue.png", Texture.class));
        stay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new CountdownScreen(game));
            }
        });
        buttons_table.add(stay).size(BUTTON_WIDTH, BUTTON_HEIGHT).padLeft(0.5f);

        buttons_table.setFillParent(true);
    }

    /**
     * Adds the background and table to the stage.
     */
    @Override
    public void show() {
        super.show();
        stage.addActor(backgroundImg);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the screen.
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        if(time != 0 && !flag) {
            if(GameController.getFromServer() == 1){
                flag = true;
                table.remove();
                createContainer();
                createButtons();
                stage.addActor(container_table);
                stage.addActor(buttons_table);
            }
            else
                game.setScreen(new CountdownScreen(game));
        }

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        time += delta;
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
        Gdx.input.setInputProcessor(null);
    }
}

