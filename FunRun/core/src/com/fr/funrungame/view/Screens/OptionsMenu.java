package com.fr.funrungame.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fr.funrungame.FunRunGame;

public class OptionsMenu extends Stage {

    /**
     * The current game session.
     */
    protected FunRunGame game;

    /**
     * The HudMenu associated to the current game being played by the User.
     */
    private HudMenu hud;

    /**
     * The table containing the elements, that will be added to the stage.
     */
    private Table table;

    /**
     * The width of the HUD viewport in pixels. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float HUD_VIEWPORT_WIDTH = 1000;

    /**
     * The height of the viewport in pixels. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float HUD_VIEWPORT_HEIGHT = HUD_VIEWPORT_WIDTH * ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());

    /**
     * Options Menu Constructor.
     * It initializes all the needed elements.
     *
     * @param viewport The viewport that will be associated to the stage.
     * @param game     The current game session.
     * @param hud      The current Hud, associated to the current game being played.
     */
    protected OptionsMenu(Viewport viewport, FunRunGame game, HudMenu hud) {
        super(viewport, game.getBatch());

        Gdx.input.setInputProcessor(this);

        this.game = game;
        this.hud = hud;

        table = new Table();

        //confStage();
    }

    /**
     * Function used to draw the Pop Up Menu in the screen.
     */
    @Override
    public void draw() {
        super.draw();
        this.act();
    }


    /**
     * Function that adds an Leave Button to the Stage.
     */
    protected void addLeaveBtn() {
//        TextButton exitBtn = new TextButton("Exit", this.skin);
//
//        exitBtn.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new LevelMenuScreen(game));
//            }
//        });
//
//        table.add(exitBtn).size(BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    /**
     * Function that adds an Stay Button to the Stage.
     */
    protected void addStayBtn() {
//        TextButton resumeBtn = new TextButton("Resume", skin);
//
//        resumeBtn.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                togglePause();
//            }
//        });
//
//        table.add(resumeBtn).size(BUTTON_WIDTH, BUTTON_HEIGHT).padBottom(BUTTON_DISTANCE).row();
    }

}
