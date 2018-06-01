package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;

/**
 * Class that handles the menu when the player finishes the race.
 */
public class FinishMenu extends OptionsMenu {

    /**
     * Table with the score.
     */
    private Table score_table;

    /**
     * Finish Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public FinishMenu(FunRunGame game){
        super(game);

        createContainer();
        createButtons();
        createScore();
    }

    @Override
    protected void createContainer() {
        container_table = new Table();

        Image container = new Image(game.getAssetManager().get("game_over.png", Texture.class));
        container_table.add(container).size(CONTAINER_WIDTH, CONTAINER_HEIGHT);

        container_table.setFillParent(true);
    }

    @Override
    protected void createButtons() {
        buttons_table = new Table();
        buttons_table.padTop(6);

        Image button = new Image(game.getAssetManager().get("mainmenu_button.png", Texture.class));
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });
        buttons_table.add(button).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        buttons_table.setFillParent(true);
    }

    /**
     * Creates the scores table.
     */
    private void createScore() {
        score_table = new Table();

        Group group_info = new Group();
        Group group_time = new Group();

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        String text;

        if(GameController.getInstance().getPlayers()[1].getTime() < GameController.getInstance().getPlayers()[0].getTime() )
            text = "You Lost!";
        else
            text = "New Best Time!";

        Label info = new Label(text, font);
        Label time = new Label(String.format("%.4f", GameController.getInstance().getPlayers()[0].getTime()), font);

        group_info.addActor(info);
        group_time.addActor(time);

        group_info.setScale(0.05f);
        group_time.setScale(0.05f);

        score_table.add().expandX();
        score_table.add().expandX();
        score_table.add(group_info).size(group_info.getWidth(),group_info.getHeight()).expandX();
        score_table.add().expandX();
        score_table.add(group_time).size(group_time.getWidth(),group_time.getHeight()).expandX();
        score_table.add().expandX();
        score_table.add().expandX();
        score_table.add().expandX();

        score_table.setFillParent(true);
    }

    /**
     * Adds the background and all the tables to the stage.
     */
    @Override
    public void show() {
        super.show();
        stage.addActor(score_table);
    }

}
