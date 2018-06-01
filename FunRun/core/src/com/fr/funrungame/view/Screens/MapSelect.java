package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.model.GameModel;

/**
 * Menu where the maps are selected.
 */
public class MapSelect extends MenuScreen {

    /**
     * Constant representing the size of the Buttons
     */
    private static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 10;

    /**
     * Constant representing the size of the Buttons
     */
    private static final float BUTTON_HEIGHT = VIEWPORT_WIDTH / 30;

    /**
     * Constant representing the size of the Maps
     */
    private static final float MAP_BUTTON_WIDTH = VIEWPORT_WIDTH / 3;

    /**
     * Constant representing the size of the Maps
     */
    private static final float MAP_BUTTON_HEIGHT = VIEWPORT_HEIGHT / 3;

    /**
     * Constant representing the distance between the first line of Level Buttons and the screen Top.
     */
    private static final float TOP_EDGE = VIEWPORT_WIDTH / 7;

    /**
     * Constant representing the distance between the stage elements and the screen limits.
     */
    private static final float SIDE_DISTANCE = VIEWPORT_WIDTH / 82;

    /**
     * Constant representing the size of the Title
     */
    private static final float TITLE_HEIGHT = VIEWPORT_HEIGHT / 7f;

    /**
     * Constant representing the size of the Title
     */
    private static final float TITLE_WIDTH =  VIEWPORT_WIDTH / 1.5f;

    /**
     * The table containing the elements, that will be added to the stage.
     */
    private Table table;

    /**
     * Map Select Screen Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    MapSelect(FunRunGame game){
        super(game, new Image(game.getAssetManager().get("mapselector.png", Texture.class)), TITLE_HEIGHT, TITLE_WIDTH);
    }

    /**
     * Creates the table.
     *
     * @param map number of the current map
     */
    private void createTable(int map) {
        table = new Table();
        table.setFillParent(true);

        addReturnButton();

        addMap1(map);
        addMap2(map);
        table.row();
        addMap3(map);
        addMap4(map);
    }

    /**
     * Adds the map number 1 button to the table.
     *
     * @param map number of the current map
     */
    private void addMap1(int map) {
        Image button;
        if(map == 1)
            button = new Image(game.getAssetManager().get("MapSelectButtons/map1_button_pressed.png", Texture.class));
        else
            button = new Image(game.getAssetManager().get("MapSelectButtons/map1_button.png", Texture.class));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameModel.getInstance().setCurrentMap(1);
                createTable(1);
                stage.addActor(table);
            }
        });

        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    /**
     * Adds the map number 2 button to the table.
     *
     * @param map number of the current map
     */
    private void addMap2(int map) {
        Image button;
        if(map == 2)
            button = new Image(game.getAssetManager().get("MapSelectButtons/map2_button_pressed.png", Texture.class));
        else
            button = new Image(game.getAssetManager().get("MapSelectButtons/map2_button.png", Texture.class));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameModel.getInstance().setCurrentMap(2);
                createTable(2);
                stage.addActor(table);
            }
        });

        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    /**
     * Adds the map number 3 button to the table.
     *
     * @param map number of the current map
     */
    private void addMap3(int map) {
        Image button;
        if(map == 3)
            button = new Image(game.getAssetManager().get("MapSelectButtons/map3_button_pressed.png", Texture.class));
        else
            button = new Image(game.getAssetManager().get("MapSelectButtons/map3_button.png", Texture.class));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameModel.getInstance().setCurrentMap(3);
                createTable(3);
                stage.addActor(table);
            }
        });
        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    /**
     * Adds the map number 4 button to the table.
     *
     * @param map number of the current map
     */
    private void addMap4(int map) {
        Image button;
        if(map == 4)
            button = new Image(game.getAssetManager().get("MapSelectButtons/map4_button_pressed.png", Texture.class));
        else
            button = new Image(game.getAssetManager().get("MapSelectButtons/map4_button.png", Texture.class));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameModel.getInstance().setCurrentMap(4);
                createTable(4);
                stage.addActor(table);
            }
        });
        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    /**
     * Adds the return button to the table.
     */
    private void addReturnButton() {
        Image backButton = new Image(game.getAssetManager().get("return.png", Texture.class));
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new MainMenu(game));
            }
        });

        table.add(backButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).top().left().padTop(TOP_EDGE/3).padLeft(SIDE_DISTANCE).row();
    }

    /**
     * Adds the table to the stage.
     */
    @Override
    public void show(){
        super.show();

        createTable(GameModel.getInstance().getCurrentMap());
        stage.addActor(table);
    }
}
