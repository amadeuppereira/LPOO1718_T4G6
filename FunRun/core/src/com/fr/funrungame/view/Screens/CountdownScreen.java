package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.PlayerModel;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;


/**
 * Class that does a countdown of 3 seconds before the game run starts.
 */
public class CountdownScreen extends GameView {

    /**
     * Constant representing the time between the numbers transitions.
     */
    private final int TIME_BETWEEN_CHANGES = 50;

    /**
     * Constant representing the Numbers Width.
     */
    private final float NUMBER_WIDTH = 80;

    /**
     * Constant representing the Numbers Height.
     */
    private final float NUMBER_HEIGHT = 80;

    /**
     * Numbers texture.
     */
    private Texture number;

    /**
     * Current number.
     */
    private int n;

    /**
     * Numbers starting positions.
     */
    private float xi, yi;

    /**
     * Initializes timer.
     */
    private int timer = TIME_BETWEEN_CHANGES;

    /**
     * Countdown Screen Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public CountdownScreen(FunRunGame game) {
        super(game);

        for(PlayerModel p: GameModel.getInstance().getPlayers())
            p.setState(PlayerModel.State.DEFAULT);
        n = 3;
        number = game.getAssetManager().get("3.png", Texture.class);
        if(Gdx.app.getType() == Application.ApplicationType.Android){
            xi = GAME_WIDTH / 2 + NUMBER_WIDTH / 2;
            yi = GAME_HEIGHT /2 + 4* NUMBER_HEIGHT;
        }
        else{
            xi = Gdx.graphics.getWidth() / 2 - (NUMBER_WIDTH / 2) + 60;
            yi = Gdx.graphics.getHeight() * 0.999999f- (NUMBER_HEIGHT / 2) + 50;
        }
    }

    /**
     * Renders the screen.
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        cameraHandler();
        // Clear the screen
        update(delta);

        Gdx.gl.glClearColor( 0/255f, 0/255f, 0/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        float x = camera.position.x - camera.viewportWidth * camera.zoom;
        float y = camera.position.y - camera.viewportHeight * camera.zoom;

        float width = camera.viewportWidth * camera.zoom * 2;
        float height = camera.viewportHeight * camera.zoom * 2;

        mapRenderer.setView(camera.combined, x, y, width, height);
        mapRenderer.render();

        game.getBatch().begin();
        drawEntities();
        game.getBatch().draw(number,  xi, yi, NUMBER_WIDTH, NUMBER_HEIGHT);
        game.getBatch().end();

    }

    /**
     * Updates the numbers.
     *
     * @param delta time since last rendered in seconds
     */
    private void update(float delta) {
        timer -= delta;
        if(timer <= 0) {
            if(n == 3) {
                timer = TIME_BETWEEN_CHANGES;
                number = game.getAssetManager().get("2.png", Texture.class);
                n = 2;
            }
            else if(n == 2) {
                timer = TIME_BETWEEN_CHANGES;
                number = game.getAssetManager().get("1.png", Texture.class);
                n = 1;
            }
            else {
                dispose();
                for(PlayerModel p: GameModel.getInstance().getPlayers()) {
                    p.setState(PlayerModel.State.RUNNING);
                }
                game.setScreen(new GameView(game));
            }
        }
    }
}
