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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.controller.entities.PlayerBody;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.model.entities.RocketPowerUpModel;
import com.fr.funrungame.model.entities.ShieldPowerUpModel;
import com.fr.funrungame.model.entities.SpeedPowerUpModel;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

public class Controllers {
    Viewport viewport;
    Stage stage;
    boolean upPressed, downPressed, powerupPressed, pausePressed;
    Image powerUpImage;

    public Controllers(FunRunGame game){
        viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        addKeysListener();
        createTable(game);
        createPowerUp(game);
        createPauseButton(game);
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

    private void createPauseButton(FunRunGame game) {
        Table table = new Table();
        Image pause_button = new Image(game.getAssetManager().get("pause.png", Texture.class));
        pause_button.setSize(50,50);
        //pause_button.setPosition(Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / (10 / ((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight()))));

        pause_button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pausePressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pausePressed = false;
            }
        });

        table.top().padTop(Gdx.graphics.getHeight() / (25 / ((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight())));
        table.setFillParent(true);
        table.add(pause_button).expandX();
        for(int i = 0; i < 8; i++){
            table.add().expandX();
        }
        stage.addActor(table);
    }

    private void createPowerUp(FunRunGame game){
        powerUpImage = new Image(game.getAssetManager().get("noPowerUp.png", Texture.class));
        powerUpImage.setSize(100,100);
        powerUpImage.setPosition(Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/12);
        stage.addActor(powerUpImage);
    }

    public void update(FunRunGame game){
        powerUpImage.remove();
        PlayerBody playerBody =  GameController.getInstance().getPlayerBody();
        if(((PlayerModel) playerBody.getUserData()).getPowerup() instanceof SpeedPowerUpModel){
            powerUpImage = new Image(game.getAssetManager().get("speed.png", Texture.class));
        }
        else if(((PlayerModel) playerBody.getUserData()).getPowerup() instanceof ShieldPowerUpModel){
            powerUpImage = new Image(game.getAssetManager().get("shield.png", Texture.class));
        }
        else if(((PlayerModel) playerBody.getUserData()).getPowerup() instanceof RocketPowerUpModel){
            powerUpImage = new Image(game.getAssetManager().get("rocket.png", Texture.class));
        }
        else{
            powerUpImage = new Image(game.getAssetManager().get("noPowerUp.png", Texture.class));
        }
        powerUpImage.setSize(100,100);
        powerUpImage.setPosition(Gdx.graphics.getWidth()/30, Gdx.graphics.getHeight()/12);

        powerUpImage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                powerupPressed = true;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                powerupPressed = false;
            }
        });

        stage.addActor(powerUpImage);
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

    public boolean isPausePressed() {
        return pausePressed;
    }

    public void resize(int width, int height){
        viewport.update(width,height);
    }
}
