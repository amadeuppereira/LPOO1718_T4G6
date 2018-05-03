package com.fr.funrungame.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.view.entities.EntityView;
import com.fr.funrungame.view.entities.PlayerView;

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
    private final OrthographicCamera camera;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;


    private List<EntityView> entityViews;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(FunRunGame game) {
        this.game = game;

        loadAssets();

        camera = createCamera();
    }


    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
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
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        // Clear the screen
        Gdx.gl.glClearColor( 0/255f, 0/255f, 0/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


        // Draw the texture
        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();
    }


    private void loadAssets(){
        game.getAssetManager().load("background_menu.png", Texture.class);
        game.getAssetManager().load("player.png", Texture.class);
        game.getAssetManager().finishLoading();

    }

    private void drawBackground(){
        //game.getBatch().draw(game.getAssetManager().get("background_menu.png", Texture.class),0,0);
        Texture background = game.getAssetManager().get("background_menu.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(GAME_WIDTH / PIXEL_TO_METER), (int) (GAME_HEIGHT / PIXEL_TO_METER));
    }

    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
//        List<AsteroidModel> asteroids = GameModel.getInstance().getAsteroids();
//        for (AsteroidModel asteroid : asteroids) {
//            EntityView view = ViewFactory.makeView(game, asteroid);
//            view.update(asteroid);
//            view.draw(game.getBatch());
//        }
//
//        List<BulletModel> bullets = GameModel.getInstance().getBullets();
//        for (BulletModel bullet : bullets) {
//            EntityView view = ViewFactory.makeView(game, bullet);
//            view.update(bullet);
//            view.draw(game.getBatch());
//        }
//
        List<PlayerModel> players = GameModel.getInstance().getPlayers();
        for (PlayerModel player : players){
            EntityView view = new PlayerView(game);
            view.update(player);
            view.draw(game.getBatch());
        }
    }
}

