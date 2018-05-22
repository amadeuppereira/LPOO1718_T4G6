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
    protected static final float BOTTOM_EDGE = VIEWPORT_WIDTH / 75;
    protected static final float DEFAULT_BUTTON_SIZE = VIEWPORT_WIDTH / 15;

    public MainMenu(FunRunGame game) {
        super(game);
    }

    private void createButtons(Table table) {
        addPlayButton(table);
        addExitButton(table);
        
    }

    private void addPlayButton(Table table) {
        Image playButton = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new CountdownScreen(game));
            }
        });
        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addExitButton(Table table) {
       Image exitButton = new Image(game.getAssetManager().get("exit_button.jpg", Texture.class));
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
}
