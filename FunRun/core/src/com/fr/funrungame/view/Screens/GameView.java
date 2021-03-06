package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.Gdx;
import com.fr.funrungame.controller.GameController;
import com.fr.funrungame.model.GameModel;
import com.fr.funrungame.model.entities.PlayerModel;
import com.fr.funrungame.view.entities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

/**
 * A view representing the game screen. Draws the game screen and
 * controls the camera.
 */
public class GameView extends ScreenAdapter {

    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.015f;

    /**
     * The width of the viewport in meters.
     */
    private static final float VIEWPORT_WIDTH = 40;

    /**
     * The height of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth());

    /**
     * The game this screen belongs to.
     */
    protected final FunRunGame game;

    /**
     * The camera used to show the viewport.
     */
    protected final OrthographicCamera camera;

    /**
     * Viewport of the game.
     */
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

    /**
     * Map's Renderer of the current level's map.
     */
    protected OrthogonalTiledMapRenderer mapRenderer;

    /**
     * HashMap containing the Game Maps.
     */
    private Map<Integer,String> gameMaps = new HashMap<Integer, String>();

    /**
     * The Player View.
     */
    private PlayerView playerView;

    /**
     * The Ghost View.
     */
    private PlayerView ghostView;

    /**
     * The Game Screen Associated HUD.
     */
    private HudMenu hudMenu;

    /**
     * The Game input handler.
     */
    private Controllers controllers;

    /**
     * Is the Game paused.
     */
    private boolean pause = false;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(FunRunGame game) {
        this.game = game;

        loadMaps();

        playerView = new PlayerView(game);
        ghostView = new PlayerView(game, 0.5f);

        camera = createCamera();

        gamePort = new FillViewport(GAME_WIDTH,GAME_HEIGHT,camera);

        hudMenu = new HudMenu(game);

        TiledMap map = game.getAssetManager().get(gameMaps.get(GameModel.getCurrentMap()), TiledMap.class);
        GameModel.getInstance().setMap(map);
        GameModel.getInstance().addEntities();

        if(mapRenderer != null)
            mapRenderer.setMap(map);
        mapRenderer = new OrthogonalTiledMapRenderer(GameModel.getInstance().getMap(), game.getBatch());

        debugRenderer = new Box2DDebugRenderer();

        controllers = new Controllers(game);
    }

    /**
     * Renders the screen.
     *
     * @param delta time since last rendered in seconds
     */
    @Override
    public void render(float delta) {
        if(pause) {
            delta = 0;
        }

        super.render(delta);

        handleInputs();

        GameController.getInstance().update(delta);

        cameraHandler();

        // Clear the screen
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

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

        game.getBatch().setProjectionMatrix(hudMenu.getStage().getCamera().combined);
        hudMenu.update(GameController.getInstance().getTime(), GameController.getInstance().getPlayerBody().isFinished());
        hudMenu.getStage().draw();

        controllers.update();
        controllers.draw();

        if (GameModel.getInstance().isFinished()) {
            end();
        }
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
     * Handles the camera position.
     */
    protected void cameraHandler(){
        float x = GameModel.getInstance().getPlayers().get(0).getX();
        float y = GameModel.getInstance().getPlayers().get(0).getY();

        if(x < 9)
            camera.position.set(9 / PIXEL_TO_METER, y / PIXEL_TO_METER + 80, 0);
        else
            camera.position.set(x / PIXEL_TO_METER, y / PIXEL_TO_METER + 80, 0);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
    }

    /**
     * Player gets to the finish line.
     */
    private void end() {
        pause = true;
        dispose();
        screenshot();
        game.setScreen(new FinishMenu(game));
    }

    /**
     * Loads all the Tiled maps.
     */
    private void loadMaps(){
        gameMaps.put(1, "maps/map1.tmx");
        gameMaps.put(2, "maps/map2.tmx");
        gameMaps.put(3, "maps/map3.tmx");
        gameMaps.put(4, "maps/map4.tmx");
        game.getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        for(String mapPath : gameMaps.values()){
            game.getAssetManager().load(mapPath, TiledMap.class);
        }
        game.getAssetManager().finishLoading();
    }

    /**
     * Draws the entities to the screen.
     */
    protected void drawEntities() {
        List<PlayerModel> players = GameModel.getInstance().getPlayers();

        ghostView.update(players.get(1));
        ghostView.draw(game.getBatch());

        playerView.update(players.get(0));
        playerView.draw(game.getBatch());
    }

    /**
     * Passes the inputs to the Game controllers.
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
            pause();
        }
    }

    /**
     * Resizes the screen.
     *
     * @param width of the screen
     * @param height of the screen
     */
    @Override
    public void resize(int width, int height){
        gamePort.update(width, height);
        controllers.resize(width,height);
        hudMenu.resize(width,height);
    }

    /**
     * Game is paused.
     */
    @Override
    public void pause () {
        super.pause();
        pause = true;
        screenshot();
        game.setScreen(new PauseMenu(game));
    }

    /**
     * Game is resumed.
     */
    @Override
    public void resume () {
        super.resume();
        pause = false;
    }

    /**
     * Takes a screenshot of the current game screen and saves to a PNG file.
     */
    private void screenshot(){
        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);

        // this loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
        for(int i = 4; i < pixels.length; i += 4) {
            pixels[i - 1] = (byte) 255;
        }

        Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
        BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
        PixmapIO.writePNG(Gdx.files.local("screenshot_background.png"), pixmap);
        pixmap.dispose();
    }
}

