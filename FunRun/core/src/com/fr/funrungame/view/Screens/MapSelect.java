package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

public class MapSelect extends MenuScreen {

    /**
     * Constant representing the size of the Buttons
     */
    private static final float BUTTON_SIZE = VIEWPORT_WIDTH / 6;

    /**
     * Constant representing the size of the Maps
     */
    private static final float MAP_WIDTH = VIEWPORT_WIDTH / 2;

    /**
     * Constant representing the size of the Maps
     */
    private static final float MAP_HEIGHT = VIEWPORT_HEIGHT / 15;

    /**
     * Constant representing the extra space around the edges of all Images.
     */
    private static final float IMAGE_EDGE = VIEWPORT_WIDTH / 75;
    /**
     * Constant representing the distance between the first line of Level Buttons and the screen Top.
     */
    private static final float TOP_EDGE = VIEWPORT_WIDTH / 7;
    /**
     * Constant representing the distance between the stage elements and the screen limits.
     */
    private static final float SIDE_DISTANCE = VIEWPORT_WIDTH / 18;
    /**
     * Constant representing the distance between the scroller and the screen bottom limit.
     */
    private static final float SCROLLER_DISTANCE = VIEWPORT_WIDTH / 35;

    Image map1;

    MapSelect(final FunRunGame game){
        super(game, new Image(game.getAssetManager().get("exit_button.png", Texture.class)));
    }


    private void createObjects(Table objects) {
        map1 = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        map1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
            }
        });
        objects.add(map1).size(MAP_WIDTH, MAP_HEIGHT).pad(IMAGE_EDGE).row();
    }

    private void createStaticObjects(Table staticObjects,Table objects) {
        //Skin skin = new Skin();
       // skin.add("Back", new Texture("title.png"));
        //TextButton back = new TextButton("Back", skin);


        //staticObjects.add(back).size(BUTTON_SIZE,BUTTON_SIZE).top().left().padLeft(SIDE_DISTANCE).padTop(TOP_EDGE/3).row();
    }

    @Override
    public void show(){
        super.show();

        Table objects = new Table();
        objects.setFillParent(true);

        Table staticObjects = new Table();
        staticObjects.setFillParent(true);

        createObjects(objects);
        createStaticObjects(staticObjects, objects);

        stage.addActor(objects);
        Gdx.input.setInputProcessor(stage);
    }
}
