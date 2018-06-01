package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

/**
 * Main menu of the game.
 */
public class MainMenu extends MenuScreen {

    /**
     * Constant representing all the Buttons' Width.
     */
    private static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing all the distance between Buttons.
     */
    private static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;

    /**
     * Constant representing all the Buttons' Size.
     */
    private static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    /**
     * Constant representing the Title Size.
     */
    private static final float TITLE_HEIGHT = VIEWPORT_HEIGHT / 3;

    /**
     * Constant representing the Title Size.
     */
    private static final float TITLE_WIDTH = VIEWPORT_WIDTH / 1.5f;

    /**
     * Constant representing the extra space around the bottom edge of the bottom Button.
     */
    private static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;

    /**
     * Constant representing the extra space around the top edge of the top Button.
     */
    private static final float TOP_EDGE = 8;

    /**
     * Play Button Image.
     */
    private Image playButton;

    /**
     * Exit Button Image.
     */
    private Image exitButton;

    /**
     * Map Select Button Image.
     */
    private Image mapSelectButton;

    /**
     * The table containing the elements, that will be added to the stage.
     */
    private Table table;

    /**
     * Main Menu Screen Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public MainMenu(FunRunGame game) {
        super(game, new Image(game.getAssetManager().get("title.png", Texture.class)), TITLE_HEIGHT, TITLE_WIDTH);
    }

    /**
     * Creates the table.
     */
    private void createTable() {
        table = new Table();
        table.setFillParent(true);
        table.padTop(TOP_EDGE);

        addPlayButton();
        addSelectMapButton();
        addExitButton();

        table.padBottom(BOTTOM_EDGE);
    }

    /**
     * Adds the play button to the table.
     */
    private void addPlayButton() {
        playButton = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        playButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                playButton = new Image(game.getAssetManager().get("play_button_pressed.png", Texture.class));
                table.reset();
                table.padTop(8);
                table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
                addSelectMapButton();
                addExitButton();
                table.padBottom(BOTTOM_EDGE);
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new LoadingScreen(game));
            }
        });
        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    /**
     * Adds the select map button to the table.
     */
    private void addSelectMapButton() {
        mapSelectButton = new Image(game.getAssetManager().get("select_map_button.png", Texture.class));
        mapSelectButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mapSelectButton = new Image(game.getAssetManager().get("select_map_button_pressed.png", Texture.class));
                table.reset();
                table.padTop(8);
                addPlayButton();
                table.add(mapSelectButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
                addExitButton();
                table.padBottom(BOTTOM_EDGE);
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new MapSelect(game));
            }
        });
        table.add(mapSelectButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    /**
     * Adds the exit button to the table.
     */
    private void addExitButton() {
        exitButton = new Image(game.getAssetManager().get("exit_button.png", Texture.class));
        exitButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                exitButton = new Image(game.getAssetManager().get("exit_button_pressed.png", Texture.class));
                table.reset();
                table.padTop(8);
                addPlayButton();
                addSelectMapButton();
                table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(2* BUTTON_EDGE).row();
                table.padBottom(BOTTOM_EDGE);
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                Gdx.app.exit();
            }
        });
        table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(2* BUTTON_EDGE).row();
    }

    /**
     * Adds the table to the stage.
     */
    @Override
    public void show() {
        super.show();
        createTable();
        stage.addActor(table);
    }
}
