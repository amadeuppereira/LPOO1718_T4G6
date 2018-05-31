package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;

import static com.fr.funrungame.controller.GameController.GAME_HEIGHT;
import static com.fr.funrungame.controller.GameController.GAME_WIDTH;

public class HudMenu {
    public Stage stage;
    private Viewport viewport;

    private float worldTimer;

    Label timeLabel;
    Label timeNumberLabel;

    Table table;

    public HudMenu(SpriteBatch sb){
        worldTimer = 0;

        viewport = new FillViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());

        stage = new Stage(viewport, sb);
        createTable();
    }

    public void createTable(){
        table = new Table();
        table.top().padTop(Gdx.graphics.getHeight() / (25 / ((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight())));
        table.setFillParent(true);

        timeNumberLabel = new Label(String.format("%.2f", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timeNumberLabel.setScale(10,10);
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.BLACK));

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

    public void resize(int width, int height){
        viewport.update(width,height);
    }
}
