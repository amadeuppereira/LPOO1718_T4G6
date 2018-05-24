package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.fr.funrungame.FunRunGame;

public class MapSelect extends MenuScreen {

    MapSelect(final FunRunGame game){
        super(game);
    }


    private void createObjects(Table objects) {

    }

    private void createStaticObjects(Table staticObjects,Table objects) {

    }

    @Override
    public void show(){

        Table objects = new Table();

        Table staticObjects = new Table();
        staticObjects.setFillParent(true);

        createObjects(objects);
        createStaticObjects(staticObjects, objects);

        stage.addActor(staticObjects);
        Gdx.input.setInputProcessor(stage);
    }
}
