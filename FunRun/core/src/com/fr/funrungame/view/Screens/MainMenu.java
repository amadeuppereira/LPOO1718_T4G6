package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

public class MainMenu extends MenuScreen {

    private Image playButton;

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
        playButton = new Image(game.getAssetManager().get("play_button.png", Texture.class));
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("PLAY!");
                game.setScreen(new GameView(game));
            }
        });
       // playButton.setSize(5, 5);
       // playButton.setPosition(50, 50);
        table.add(playButton).size(BUTTON_WIDTH, DEFAULT_BUTTON_SIZE).pad(BUTTON_EDGE).row();
    }

    private void addExitButton(Table table) {

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
