package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;
import com.fr.funrungame.controller.GameController;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private float worldTimer;
    private Integer position;

    Label timeLabel;
    Label positionNumberLabel;
    Label positionLabel;
    Label nothingLabel;
    Label timeNumberLabel;

    Table table;

    public Hud(SpriteBatch sb){
        worldTimer = 0;
        position = 1;

        viewport = new FitViewport(GameController.GAME_WIDTH,GameController.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        createTable();
    }

    public void createTable(){
        table = new Table();
        table.top();
        table.setFillParent(true);

        timeNumberLabel = new Label(String.format("%.2f", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        positionNumberLabel = new Label(String.format("%01d", position), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        positionLabel = new Label("POSITION", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nothingLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(timeLabel).expandX().padTop(10);
        table.add(nothingLabel).expandX().padTop(10);
        table.add(nothingLabel).expandX().padTop(10);
        table.add(positionLabel).expandX().padTop(10);
        table.row();
        table.add(timeNumberLabel).expandX();
        table.add(nothingLabel).expandX();
        table.add(nothingLabel).expandX();
        table.add(positionNumberLabel).expandX();

        stage.addActor(table);
    }

    public void update(float delta, boolean finish){
        if(!finish) {
            worldTimer += delta;
            if (stage != null && table != null) {
                stage.clear();
                table.reset();
                createTable();
            }
        }
    }
}
