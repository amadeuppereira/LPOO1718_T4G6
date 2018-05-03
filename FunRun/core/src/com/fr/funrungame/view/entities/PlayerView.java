package com.fr.funrungame.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fr.funrungame.FunRunGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fr.funrungame.model.entities.EntityModel;
import com.fr.funrungame.model.entities.PlayerModel;

public class PlayerView extends EntityView {

    private static int currentSkinID  = 0;

    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.08f;

    /**
     * The animation used when the player is running
     */
    private Animation<TextureRegion> runningAnimation;

    /**
     * The animation used when the player is jumping
     */
    private Animation<TextureRegion> jumpingAnimation;

    /**
     * The texture used when the player is not running
     */
    private TextureRegion notRunningRegion;

    /**
     * Time since the player started the game. Used
     * to calculate the frame to show in animations.
     */
    private float stateTime;

    /**
     * Is the player running.
     */
    private boolean running;

    /**
     * Is the player jumping.
     */
    private boolean jumping;

    public PlayerView(FunRunGame game){
        super(game);
        stateTime = 0;
    }

    public Sprite createSprite(FunRunGame game) {
        runningAnimation = createRunningAnimation(game);
        jumpingAnimation = createJumpingAnimation(game);
        notRunningRegion = createNotRunningRegion(game);

        return new Sprite(notRunningRegion);
    }

    private TextureRegion createNotRunningRegion(FunRunGame game){
        Texture texture = game.getAssetManager().get("player.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private Animation<TextureRegion> createRunningAnimation(FunRunGame game) {
        Texture thrustTexture = game.getAssetManager().get("player_running.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 6, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[6];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 6);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    private Animation<TextureRegion> createJumpingAnimation(FunRunGame game) {
        Texture thrustTexture = game.getAssetManager().get("player_jumping.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 2, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[2];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 2);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    @Override
    public void update(EntityModel model) {
        super.update(model);

        jumping = ((PlayerModel)model).isJumping();
        ((PlayerModel)model).setJumping(false);

        running = ((PlayerModel)model).isRunning();
        ((PlayerModel)model).setRunning(false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if(jumping)
            sprite.setRegion(jumpingAnimation.getKeyFrame(stateTime, true));
        else if (running)
            sprite.setRegion(runningAnimation.getKeyFrame(stateTime, true));
        else
            sprite.setRegion(notRunningRegion);

        sprite.draw(batch);
    }

    public static void setCurrentSkin(int id){
        currentSkinID = id;
    }
}
