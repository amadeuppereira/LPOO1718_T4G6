package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;

/**
 * Classe that pauses the current game. It can be resumed or exited.
 */
public class PauseMenu extends OptionsMenu {

    /**
     * Pause Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public PauseMenu(FunRunGame game){
        super(game);

        createContainer();
        createButtons();
    }

    @Override
    protected void createContainer() {
        container_table = new Table();

        Image container = new Image(game.getAssetManager().get("pause_menu.png", Texture.class));
        container_table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT);

        container_table.setFillParent(true);
    }


    @Override
    protected void createButtons() {
        buttons_table = new Table();
        buttons_table.padTop(6);

        Image leave = new Image(game.getAssetManager().get("leave_button.png", Texture.class));
        leave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });
        buttons_table.add(leave).size(BUTTON_WIDTH, BUTTON_HEIGHT).padRight(1f);

        Image stay = new Image(game.getAssetManager().get("stay_button.png", Texture.class));
        stay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new GameView(game));
            }
        });
        buttons_table.add(stay).size(BUTTON_WIDTH, BUTTON_HEIGHT).padLeft(0.5f);

        buttons_table.setFillParent(true);
    }


}
