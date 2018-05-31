package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

import java.util.ArrayList;

public class MapSelect extends MenuScreen {

    /**
     * Constant representing the size of the Buttons
     */
    private static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 10;

    /**
     * Constant representing the size of the Buttons
     */
    private static final float BUTTON_HEIGHT = VIEWPORT_WIDTH / 20;

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

    private static final float TITLE_HEIGHT = VIEWPORT_HEIGHT / 8.94f;

    private static final float TITLE_WIDTH =  VIEWPORT_WIDTH / 1.5f;

    MapSelect(final FunRunGame game){
        super(game, new Image(game.getAssetManager().get("mapselector.png", Texture.class)), TITLE_HEIGHT, TITLE_WIDTH);
        System.out.println(TITLE_WIDTH);

    }

    private void createButtons(Table table) {
        addReturnButton(table);

        addMap1(table);
        addMap2(table);
        table.row();
        addMap3(table);
        addMap4(table);

    }

    private void addMap1(Table table) {
        Image button = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map1");
                dispose();
            }
        });

        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    private void addMap2(Table table) {
        Image button = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map2");
                dispose();
            }
        });

        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    private void addMap3(Table table) {
        Image button = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map3");
                dispose();
            }
        });
        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    private void addMap4(Table table) {
        Image button = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map4");
                dispose();
            }
        });
        table.add(button).size(MAP_BUTTON_WIDTH, MAP_BUTTON_HEIGHT).fill().expand();
    }

    private void addReturnButton(Table table) {
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

    @Override
    public void show(){
//        super.show();
//
//        staticObjects.setFillParent(true);
//
//        stage.addActor(staticObjects);
//
//        Gdx.input.setInputProcessor(stage);

        super.show();
        Table table = new Table();
        table.setFillParent(true);

        createButtons(table);
        table.debug();
        stage.addActor(table);
    }
}
