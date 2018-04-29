package com.fr.funrungame.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.view.entities.EntityView;

import java.util.List;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

public class GameView extends ScreenAdapter {

    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_WIDTH = 30;


    /**
     * The game this screen belongs to.
     */
    private final FunRunGame game;

    /**
     * The camera.
     */
    //private final OrthographicCamera camera;


    private List<EntityView> entityViews;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(FunRunGame game) {
        this.game = game;

        loadAssets();

        //camera = createCamera();
    }

    /**
     * Renders the screen
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        // Update the camera
       // camera.update();
        //game.getBatch().setProjectionMatrix(camera.combined);

        // Clear the screen
        Gdx.gl.glClearColor( 0/255f, 0/255f, 0/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


        // Draw the texture
        game.getBatch().begin();
        drawBackground();
        game.getBatch().end();
    }

    //private OrthographicCamera createCamera(){
        //float ratio = ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());
       // OrthographicCamera camera = new OrthographicCamera(
         //       VIEWPORT_WIDTH / PIXEL_TO_METER,
          //      VIEWPORT_WIDTH / PIXEL_TO_METER * ratio
        //);

        //camera.position.set(camera.viewportWidth,camera.viewportHeight,0);
        //return camera;
   // }

    private void loadAssets(){
        game.getAssetManager().load("background_menu.png", Texture.class);
        game.getAssetManager().load("alien.png", Texture.class);
        game.getAssetManager().finishLoading();

    }

    private void drawBackground(){
        game.getBatch().draw(game.getAssetManager().get("background_menu.png", Texture.class),0,0);
    }
}

