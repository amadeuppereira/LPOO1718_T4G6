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
import com.fr.funrungame.model.GameModel;

/**
 * Class that handles the menu when the player finishes the race.
 */
public class FinishMenu extends ScreenAdapter {

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
     * Finish Menu screen viewport.
     */
    private Viewport viewport;

    /**
     * Finish Menu screen stage.
     */
    private Stage stage;

    /**
     * Finish Menu screen background image.
     */
    private Image backgroundImg;

    /**
     * Table with the container.
     */
    private Table container_table;

    /**
     * Table with the buttons.
     */
    private Table button_table;

    /**
     * Table with the score.
     */
    private Table score_table;

    /**
     * Finish Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public FinishMenu(FunRunGame game){
        this.game = game;
        this.viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.viewport.apply();

        stage = new Stage(this.viewport, game.getBatch());

        backgroundImg = new Image(new Texture(Gdx.files.local("screenshot_background.png")));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        createContainer();
        createButton();
        createScore();
    }

    /**
     * Creates the container table.
     */
    private void createContainer() {
        container_table = new Table();

        Image container = new Image(game.getAssetManager().get("game_over.png", Texture.class));
        container_table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT);

        container_table.setFillParent(true);
    }

    /**
     * Creates the buttons table.
     */
    private void createButton() {
        button_table = new Table();
        button_table.padTop(6);

        Image button = new Image(game.getAssetManager().get("mainmenu_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                GameController.reset();
                GameModel.reset();
                game.setScreen(new MainMenu(game));
            }
        });
        button_table.add(button).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        button_table.setFillParent(true);
    }

    /**
     * Creates the scores table.
     */
    private void createScore() {
        score_table = new Table();

        Group group_info = new Group();
        Group group_time = new Group();

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        String text;

        if(GameController.getInstance().getPlayers()[1].getTime() < GameController.getInstance().getPlayers()[0].getTime() )
            text = "You Lost!";
        else
            text = "New Best Time!";

        Label info = new Label(text, font);
        Label time = new Label(String.format("%.4f", GameController.getInstance().getPlayers()[0].getTime()), font);

        group_info.addActor(info);
        group_time.addActor(time);

        group_info.setScale(0.05f);
        group_time.setScale(0.05f);

        score_table.add().expandX();
        score_table.add().expandX();
        score_table.add(group_info).size(group_info.getWidth(),group_info.getHeight()).expandX();
        score_table.add().expandX();
        score_table.add(group_time).size(group_time.getWidth(),group_time.getHeight()).expandX();
        score_table.add().expandX();
        score_table.add().expandX();
        score_table.add().expandX();

        score_table.setFillParent(true);
    }

    /**
     * Adds the background and all the tables to the stage.
     */
    @Override
    public void show() {
        stage.addActor(backgroundImg);
        stage.addActor(container_table);
        stage.addActor(button_table);
        stage.addActor(score_table);

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
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
