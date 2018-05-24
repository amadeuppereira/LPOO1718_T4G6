package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

public class MainMenu extends MenuScreen {

    protected static final float BUTTON_WIDTH = VIEWPORT_WIDTH / 2;
    protected static final float BUTTON_EDGE = VIEWPORT_WIDTH / 75;
    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    Image playButton;
    Image exitButton;
    Image customizeButton;

    public MainMenu(FunRunGame game) {
        super(game);
    }

    private void createButtons(Table table) {
        addPlayButton(table);
        addCustomizeButton(table);
        addExitButton(table);
    }

    private void addPlayButton(final Table table) {
        playButton = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        playButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                playButton = new Image(game.getAssetManager().get("play_button_pressed.png", Texture.class));
                table.reset();
                table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
                addCustomizeButton(table);
                addExitButton(table);
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                game.setScreen(new CountdownScreen(game));
            }
        });
        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addCustomizeButton(final Table table) {
        customizeButton = new Image(game.getAssetManager().get("customize_button.png", Texture.class));
        customizeButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                customizeButton = new Image(game.getAssetManager().get("customize_button_pressed.png", Texture.class));
                table.reset();
                addPlayButton(table);
                table.add(customizeButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
                addExitButton(table);
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                //do something here
            }
        });
        table.add(customizeButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addExitButton(final Table table) {
       exitButton = new Image(game.getAssetManager().get("exit_button.png", Texture.class));
        exitButton.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                exitButton = new Image(game.getAssetManager().get("exit_button_pressed.png", Texture.class));
                table.reset();
                addPlayButton(table);
                addCustomizeButton(table);
                table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                Gdx.app.exit();
            }
        });
        table.add(exitButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    @Override
    public void show() {
        super.show();

        Table table = new Table();
        table.setFillParent(true);
        createButtons(table);
        stage.addActor(table);
    }

    public void update(){

    }
}
