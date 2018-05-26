package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

public class Controller {
    Viewport viewport;
    Stage stage;
    boolean upPressed, downPressed, powerupPressed;
    OrthographicCamera camera;

    public Controller(FunRunGame game){
        camera = new OrthographicCamera();
        //viewport = new FitViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, camera);
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        addKeysListener();
        createTable(game);
    }

    private void addKeysListener(){
        stage.addListener(new InputListener(){

            @Override
            public boolean keyDown(InputEvent event, int keycode){
                switch (keycode){
                    case Input.Keys.UP:
                        upPressed = true;
                        break;
                    case Input.Keys.DOWN:
                        downPressed = true;
                        break;
                    case Input.Keys.SPACE:
                        powerupPressed = true;
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode){
                switch (keycode){
                    case Input.Keys.UP:
                        upPressed = false;
                        break;
                    case Input.Keys.DOWN:
                        downPressed = false;
                        break;
                    case Input.Keys.SPACE:
                        powerupPressed = false;
                        break;
                }
                return true;
            }
        });
    }

    private void createTable(FunRunGame game){
        Table table = new Table();
        table.padLeft(GAME_WIDTH * 2 - 300);
        table.padBottom(Gdx.graphics.getHeight() / 4);

        Image arrow_up_button = new Image(game.getAssetManager().get("arrow_up.png", Texture.class));
        arrow_up_button.setSize(100,100);
        arrow_up_button.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                upPressed = false;
            }
        });

        Image arrow_down_button = new Image(game.getAssetManager().get("arrow_down.png", Texture.class));
        arrow_down_button.setSize(100,100);
        arrow_down_button.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
            }
        });

        table.add(arrow_down_button).size(arrow_down_button.getWidth(), arrow_down_button.getHeight()).pad(10);
        table.add(arrow_up_button).size(arrow_up_button.getWidth(), arrow_up_button.getHeight());

        stage.addActor(table);
    }

    public void draw(){
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isPowerupPressed() {
        return powerupPressed;
    }

    public void resize(int width, int height){
        viewport.update(width,height);
    }
}
