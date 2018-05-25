package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.fr.funrungame.FunRunGame;

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
    private static final float MAP_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing the size of the Maps
     */
    private static final float MAP_HEIGHT = VIEWPORT_HEIGHT / 3;

    /**
     * Constant representing the extra space around the edges of all Images.
     */
    private static final float IMAGE_EDGE = VIEWPORT_WIDTH / 40;
    /**
     * Constant representing the distance between the first line of Level Buttons and the screen Top.
     */
    private static final float TOP_EDGE = VIEWPORT_WIDTH / 7;
    /**
     * Constant representing the distance between the stage elements and the screen limits.
     */
    private static final float SIDE_DISTANCE = VIEWPORT_WIDTH / 82;

    private static final float MAP_LABEL_WIDTH = VIEWPORT_WIDTH / 10;

    private static final float MAP_LABEL_HEIGHT = VIEWPORT_WIDTH / 30;

    private static final float MAP_TITLE_EDGE = VIEWPORT_WIDTH / 80;

    MapSelect(final FunRunGame game){
        super(game, new Image(game.getAssetManager().get("mapselector.png", Texture.class)), 30, 4.47f);
    }


    private void createObjects(Table objects) {
        Image map1 = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        map1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map1");
                dispose();
            }
        });
        objects.add(map1).size(MAP_WIDTH, MAP_HEIGHT).pad(IMAGE_EDGE).row();
        Image map1_title = new Image(game.getAssetManager().get("title.png", Texture.class));
        objects.add(map1_title).size(MAP_LABEL_WIDTH, MAP_LABEL_HEIGHT).pad(MAP_TITLE_EDGE).row();

        Image map2 = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        map2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map2");
                dispose();
            }
        });
        objects.add(map2).size(MAP_WIDTH, MAP_HEIGHT).pad(IMAGE_EDGE).row();
        Image map2_title = new Image(game.getAssetManager().get("title.png", Texture.class));
        objects.add(map2_title).size(MAP_LABEL_WIDTH, MAP_LABEL_HEIGHT).pad(MAP_TITLE_EDGE).row();

        Image map3 = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        map3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map3");
                dispose();
            }
        });
        objects.add(map3).size(MAP_WIDTH, MAP_HEIGHT).pad(IMAGE_EDGE).row();
        Image map3_title = new Image(game.getAssetManager().get("title.png", Texture.class));
        objects.add(map3_title).size(MAP_LABEL_WIDTH, MAP_LABEL_HEIGHT).pad(MAP_TITLE_EDGE).row();

        Image map4 = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        map4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("map4");
                dispose();
            }
        });
        objects.add(map4).size(MAP_WIDTH, MAP_HEIGHT).pad(IMAGE_EDGE).row();
        Image map4_title = new Image(game.getAssetManager().get("title.png", Texture.class));
        objects.add(map4_title).size(MAP_LABEL_WIDTH, MAP_LABEL_HEIGHT).pad(MAP_TITLE_EDGE).row();
    }

    private void createStaticObjects(Table staticObjects,Table objects) {
        Image backButton = new Image(game.getAssetManager().get("return.png", Texture.class));
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

        ScrollPane scroller = new ScrollPane(objects);

        staticObjects.add(backButton).size(BUTTON_WIDTH,BUTTON_HEIGHT).top().left().padLeft(SIDE_DISTANCE).padTop(TOP_EDGE/3).row();
        staticObjects.add(scroller).padTop(TOP_EDGE/2).fill().expand();
    }

    @Override
    public void show(){
        super.show();

        Table objects = new Table();

        Table staticObjects = new Table();
        staticObjects.setFillParent(true);

        createObjects(objects);
        createStaticObjects(staticObjects, objects);

        stage.addActor(staticObjects);

        stage.setDebugAll(true);

        Gdx.input.setInputProcessor(stage);
    }
}
