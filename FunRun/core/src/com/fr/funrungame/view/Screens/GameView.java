package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.view.entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

public class GameView extends ScreenAdapter {

    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = true;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.015f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_WIDTH = 40;

    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());

    /**
     * The game this screen belongs to.
     */
    protected final FunRunGame game;

    /**
     * The camera.
     */
    protected final OrthographicCamera camera;

    private Viewport gamePort;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;

    protected OrthogonalTiledMapRenderer mapRenderer;

    private Map<Integer,String> gameMaps = new HashMap<Integer, String>();

    PlayerView playerView;
    PlayerView ghostView;

    private HudMenu hudMenu;

    Controllers controllers;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(FunRunGame game) {
        this.game = game;

        loadAssets();

        playerView = new PlayerView(game);
        ghostView = new PlayerView(game, 0.5f);

        camera = createCamera();

        gamePort = new FillViewport(GAME_WIDTH,GAME_HEIGHT,camera);

        hudMenu = new HudMenu(game.getBatch());

        TiledMap map = game.getAssetManager().get(gameMaps.get(GameModel.getInstance().getCurrentMap()), TiledMap.class);
        GameModel.getInstance().setMap(map);
        GameModel.getInstance().addEntities();

        if(mapRenderer != null)
            mapRenderer.setMap(map);
        mapRenderer = new OrthogonalTiledMapRenderer(GameModel.getInstance().getMap(), game.getBatch());

        debugRenderer = new Box2DDebugRenderer();

        controllers = new Controllers(game);
    }


    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_HEIGHT / PIXEL_TO_METER);
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

        handleInputs();

        GameController.getInstance().update(delta);

        cameraHandler();

        // Clear the screen
        Gdx.gl.glClearColor( 0/255f, 0/255f, 0/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        float x = camera.position.x - camera.viewportWidth * camera.zoom;
        float y = camera.position.y - camera.viewportHeight * camera.zoom;

        float width = camera.viewportWidth * camera.zoom * 2;
        float height = camera.viewportHeight * camera.zoom * 2;

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(GameController.getInstance().getWorld(), debugCamera);
        }

        mapRenderer.setView(camera.combined, x, y, width, height);
        mapRenderer.render();

        game.getBatch().begin();
        drawEntities();
        game.getBatch().end();

        game.getBatch().setProjectionMatrix(hudMenu.stage.getCamera().combined);
        hudMenu.update(GameController.getInstance().getTime(), GameController.getInstance().getPlayerBody().isFinished());
        hudMenu.stage.draw();

        controllers.update(game);
        controllers.draw();

        if(GameModel.getInstance().isFinished()) end();

    }

    protected void cameraHandler(){
        float x = GameModel.getInstance().getPlayers().get(0).getX();
        float y = GameModel.getInstance().getPlayers().get(0).getY();

        if(x < 8)
            camera.position.set(8 / PIXEL_TO_METER, y / PIXEL_TO_METER + 80, 0);
        else
            camera.position.set(x / PIXEL_TO_METER, y / PIXEL_TO_METER + 80, 0);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
    }

    private void end() {
        dispose();
        GameController.reset();
        GameModel.reset();
        game.setScreen(new MainMenu(game));
    }

    private void loadAssets(){
        game.getAssetManager().load("background_menu.png", Texture.class);
        game.getAssetManager().load("player.png", Texture.class);
        game.getAssetManager().load("player_running.png", Texture.class);
        game.getAssetManager().load("player_jumping.png", Texture.class);
        game.getAssetManager().load("player_falling.png", Texture.class);
        game.getAssetManager().load("player_shielded.png", Texture.class);
        game.getAssetManager().load("player_running_shielded.png", Texture.class);
        game.getAssetManager().load("player_jumping_shielded.png", Texture.class);
        game.getAssetManager().load("player_falling_shielded.png", Texture.class);
        game.getAssetManager().load("rocket.png", Texture.class);
        game.getAssetManager().load("speed.png", Texture.class);
        game.getAssetManager().load("shield.png", Texture.class);
        game.getAssetManager().load("noPowerUp.png", Texture.class);
        game.getAssetManager().load("pause.png", Texture.class);
        loadMaps();
        game.getAssetManager().finishLoading();

    }

    private void loadMaps(){
        gameMaps.put(1, "maps/map3.tmx");
        game.getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        for(String mapPath : gameMaps.values()){
            game.getAssetManager().load(mapPath, TiledMap.class);
        }
    }

    /**
     * Draws the entities to the screen.
     */
    protected void drawEntities() {
        List<PlayerModel> players = GameModel.getInstance().getPlayers();

        playerView.update(players.get(0));
        playerView.draw(game.getBatch());

        ghostView.update(players.get(1));
        ghostView.draw(game.getBatch());

//        for (PlayerModel player : players){
//            playerView.update(player);
//            playerView.draw(game.getBatch());
//        }
    }

    /**
     * Handles any inputs and passes them to the controllers.
     */
    private void handleInputs() {
        if(controllers.isUpPressed()){
            GameController.getInstance().jump(GameController.getInstance().getPlayerBody());
        }
        if(controllers.isDownPressed()){
            GameController.getInstance().moveDown(GameController.getInstance().getPlayerBody());
        }
        if(controllers.isPowerupPressed()){
            GameController.getInstance().usePowerUp(GameController.getInstance().getPlayerBody());
        }
        if(controllers.isPausePressed()){
            System.out.println("pause pressed");
        }
    }

    @Override
    public void resize(int width, int height){
        gamePort.update(width, height);
        controllers.resize(width,height);
        hudMenu.resize(width,height);
    }

    protected GameView getThis() {
        return this;
    }
}

