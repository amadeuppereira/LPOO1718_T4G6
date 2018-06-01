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

/**
 * Class that handles all the user inputs from the keyboard or the touch screen.
 */
public class Controllers {
    /**
     * Constant representing the Arrow Button Size.
     */
    private static final float ARROW_BUTTON_SIZE = 100;

    /**
     * Constant representing the Pause Button Size.
     */
    private static final float PAUSE_BUTTON_SIZE = 50;

    /**
     * Constant representing the Power Up Button Size.
     */
    private static final float POWERUP_BUTTON_SIZE = 100;

    /**
     * Constant representing the Power Up X Position.
     */
    private static final float POWERUP_X_POSITION = Gdx.graphics.getWidth()/30;

    /**
     * Constant representing the Power Up Y Position.
     */
    private static final float POWERUP_Y_POSITION = Gdx.graphics.getHeight()/12;

    /**
     * The current game session.
     */
    private FunRunGame game;

    /**
     * Controllers viewport.
     */
    private Viewport viewport;

    /**
     * Controllers stage.
     */
    private Stage stage;

    /**
     * Is the up key pressed.
     */
    private boolean upPressed;

    /**
     * Is the up down pressed.
     */
    private boolean downPressed;

    /**
     * Is the power up pressed.
     */
    private boolean powerupPressed;

    /**
     * Is the pause button pressed.
     */
    private boolean pausePressed;

    /**
     * Current power up image.
     */
    private Image powerUpImage;

    /**
     * Controllers Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public Controllers(FunRunGame game){
        this.game = game;

        viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);

        addKeysListener();
        createTable();
        createPowerUp();
        createPauseButton();
    }

    /**
     * Adds input listener to the keys needed.
     */
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
                    case Input.Keys.ESCAPE:
                        pausePressed=true;
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

    /**
     * Creates the table and adds it to the stage.
     */
    private void createTable(){
        Table table = new Table();
        table.padLeft(GAME_WIDTH * 2 - 300);
        table.padBottom(Gdx.graphics.getHeight() / 4);

        Image arrow_up_button = new Image(game.getAssetManager().get("arrow_up.png", Texture.class));
        arrow_up_button.setSize(ARROW_BUTTON_SIZE,ARROW_BUTTON_SIZE);
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
        arrow_down_button.setSize(ARROW_BUTTON_SIZE,ARROW_BUTTON_SIZE);
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

    /**
     * Creates the pause button and adds it to the stage.
     */
    private void createPauseButton() {
        Table table = new Table();
        Image pause_button = new Image(game.getAssetManager().get("pause.png", Texture.class));
        pause_button.setSize(PAUSE_BUTTON_SIZE,PAUSE_BUTTON_SIZE);

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

    /**
     * Creates the power up button and adds it to the stage.
     */
    private void createPowerUp(){
        powerUpImage = new Image(game.getAssetManager().get("noPowerUp.png", Texture.class));
        powerUpImage.setSize(POWERUP_BUTTON_SIZE,POWERUP_BUTTON_SIZE);
        powerUpImage.setPosition(POWERUP_X_POSITION, POWERUP_Y_POSITION);
        stage.addActor(powerUpImage);
    }

    /**
     * Updates the power up button and replaces itself in the stage.
     */
    public void update(){
        powerUpImage.remove();

        setPowerUpImage();

        powerUpImage.setSize(POWERUP_BUTTON_SIZE,POWERUP_BUTTON_SIZE);
        powerUpImage.setPosition(POWERUP_X_POSITION, POWERUP_Y_POSITION);

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

    /**
     * Changes the power up image depending on the current power up.
     */
    private void setPowerUpImage(){
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
    }

    /**
     * Draws the stage.
     */
    public void draw(){
        stage.draw();
    }

    /**
     * Returns true if up key is pressed.
     *
     * @return upPressed
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Returns true if down key is pressed.
     *
     * @return downPressed
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Returns true if power up is pressed.
     *
     * @return powerupPressed
     */
    public boolean isPowerupPressed() {
        return powerupPressed;
    }

    /**
     * Returns true if pause up is pressed.
     *
     * @return pausePressed
     */
    public boolean isPausePressed() {
        return pausePressed;
    }

    /**
     * Resizes the screen.
     *
     * @param width of the screen.
     * @param height of the screen.
     */
    public void resize(int width, int height){
        viewport.update(width,height);
    }
}
