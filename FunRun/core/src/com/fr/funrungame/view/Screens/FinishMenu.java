package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.Screen;



public class FinishMenu extends ScreenAdapter {

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_WIDTH = 40;

    /**
     * The height of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    protected static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    protected static final float CONTAINER_WIDTH = VIEWPORT_WIDTH / 2;

    protected static final float CONTAINER_HEIGHT = VIEWPORT_HEIGHT / 3;

    private Viewport viewport;
    private Stage stage;
    private Image backgroundImg;
    private Table table;

    private FunRunGame game;

    public FinishMenu(FunRunGame game){
        this.game = game;
        this.viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.viewport.apply();

        stage = new Stage(this.viewport, game.getBatch());

        backgroundImg = new Image(new Texture(Gdx.files.local("screenshot_background.png")));
        backgroundImg.setScale(VIEWPORT_WIDTH / backgroundImg.getWidth(), VIEWPORT_HEIGHT / backgroundImg.getHeight());

        createTable();

        //Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        //Label pause_label = new Label("PAUSE", font);
        //table.add(pause_label).expandX().row();
    }

    private void createTable() {
        table = new Table();

        Image container = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT).expand().pad(10);

        table.setFillParent(true);
    }


    @Override
    public void show() {
        stage.addActor(backgroundImg);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.justTouched()){
            dispose();
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        Gdx.files.local("screenshot_background.png").delete();
        Gdx.input.setInputProcessor(null);
    }
}
