package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

/**
 * Hud menu used on the game run to show the current time of the run.
 */
public class HudMenu {
    /**
     * Hud Menu screen viewport.
     */
    private Viewport viewport;

    /**
     * Hud Menu screen stage.
     */
    private Stage stage;

    /**
     * Time counter.
     */
    private float worldTimer;

    /**
     * The table containing the elements, that will be added to the stage.
     */
    private Table table;

    /**
     * Hud Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param game The current game session.
     */
    public HudMenu(FunRunGame game){
        worldTimer = 0;

        viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, game.getBatch());
        createTable();
    }

    /**
     * Creates the table.
     */
    private void createTable(){
        table = new Table();
        table.top().padTop(Gdx.graphics.getHeight() / (25 / ((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight())));
        table.setFillParent(true);

        Label timeNumberLabel = new Label(String.format("%.2f", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timeNumberLabel.setScale(10,10);
        Label timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add().expandX().padTop(10);
        table.add().expandX().padTop(10);
        table.add().expandX().padTop(10);
        table.add().expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add().expandX();
        table.add().expandX();
        table.add().expandX();
        table.add().expandX();
        table.add(timeNumberLabel).expandX();

        stage.addActor(table);
    }

    /**
     * Updates the timer.
     *
     * @param time current game time.
     * @param finish true if the player already finished
     */
    public void update(float time, boolean finish){
        if(!finish) {
            worldTimer = time + 0.01f;
            if (stage != null && table != null) {
                stage.clear();
                table.reset();
                createTable();
            }
        }
    }

    /**
     * Resizes the screen.
     *
     * @param width of the screen.
     * @param height of the screen.
     */
    public void resize(int width, int height){
        viewport.update(width,height);
    }

    /**
     * Returns the Hud stage.
     *
     * @return stage.
     */
    public Stage getStage() {
        return stage;
    }
}
