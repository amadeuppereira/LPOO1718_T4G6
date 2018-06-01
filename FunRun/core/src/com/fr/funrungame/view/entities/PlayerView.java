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

/**
 * A view representing a player
 */
public class PlayerView extends EntityView {

    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.09f;

    /**
     * The animation used when the player is running
     */
    private Animation<TextureRegion> runningAnimation;

    /**
     * The texture used when the player is not running
     */
    private TextureRegion notRunningRegion;

    /**
     * The texture used when the player is jumping
     */
    private TextureRegion jumpingRegion;

    /**
     * The texture used when the player is falling
     */
    private TextureRegion fallingRegion;

    /**
     * The texture used when the player is shielded
     */
    private TextureRegion shieldRegion;

    /**
     * The animation used when the player is running with a shield
     */
    private Animation<TextureRegion> runningShieldedAnimation;

    /**
     * The texture used when the player is jumping with a shield
     */
    private TextureRegion jumpingShieldedRegion;

    /**
     * The texture used when the player is falling with a shield
     */
    private TextureRegion fallingShieldedRegion;

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

    /**
     * Is the player falling.
     */
    private boolean falling;

    /**
     * Is the player invulnerable
     */
    private boolean invulnerable;

    /**
     * Is the player dead
     */
    private boolean dead;

    /**
     * Is the player shielded
     */
    private boolean shield;

    private float alpha = 1f;
    private float dead_alpha = .0f;

    public PlayerView(FunRunGame game){
        super(game);
        stateTime = 0;
    }

    public PlayerView(FunRunGame game, float alpha) {
        this(game);
        this.alpha = alpha;
    }

    public Sprite createSprite(FunRunGame game) {
        runningAnimation = createRunningAnimation(game);
        notRunningRegion = createNotRunningRegion(game);
        jumpingRegion = createJumpingRegion(game);
        fallingRegion = createFallingRegion(game);
        shieldRegion = createShieldRegion(game);
        runningShieldedAnimation = createRunningShieldedAnimation(game);
        jumpingShieldedRegion = createJumpingShieldedRegion(game);
        fallingShieldedRegion = createFallingShieldedRegion(game);


        return new Sprite(notRunningRegion);
    }

    private TextureRegion createNotRunningRegion(FunRunGame game){
        Texture texture = game.getAssetManager().get("player.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private TextureRegion createJumpingRegion(FunRunGame game){
        Texture texture = game.getAssetManager().get("player_jumping.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private TextureRegion createFallingRegion(FunRunGame game){
        Texture texture = game.getAssetManager().get("player_falling.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private TextureRegion createShieldRegion(FunRunGame game){
        Texture texture = game.getAssetManager().get("player_shielded.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private Animation<TextureRegion> createRunningAnimation(FunRunGame game) {
        Texture thrustTexture = game.getAssetManager().get("player_running.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 6, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[6];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 6);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    private TextureRegion createFallingShieldedRegion(FunRunGame game) {
        Texture texture = game.getAssetManager().get("player_falling_shielded.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private TextureRegion createJumpingShieldedRegion(FunRunGame game) {
        Texture texture = game.getAssetManager().get("player_jumping_shielded.png");
        return new TextureRegion(texture, texture.getWidth(), texture.getHeight());
    }

    private Animation<TextureRegion> createRunningShieldedAnimation(FunRunGame game) {
        Texture thrustTexture = game.getAssetManager().get("player_running_shielded.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 6, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[6];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 6);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    @Override
    public void update(EntityModel model) {
        super.update(model);

        jumping = ((PlayerModel)model).isJumping();
        running = ((PlayerModel)model).isRunning();
        falling = ((PlayerModel)model).isFalling();
        invulnerable = ((PlayerModel)model).isInvulnerable();
        dead = ((PlayerModel)model).isDead();
        shield = ((PlayerModel)model).isShield();
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if(shield){
            if(jumping)
                sprite.setRegion(jumpingShieldedRegion);
            else if(falling)
                sprite.setRegion(fallingShieldedRegion);
            else if (running)
                sprite.setRegion(runningShieldedAnimation.getKeyFrame(stateTime, true));
            else
                sprite.setRegion(shieldRegion);
        }
        else{
            if(jumping)
                sprite.setRegion(jumpingRegion);
            else if(falling)
                sprite.setRegion(fallingRegion);
            else if (running)
                sprite.setRegion(runningAnimation.getKeyFrame(stateTime, true));
            else
                sprite.setRegion(notRunningRegion);
        }

        if(dead) {
            sprite.draw(batch, 0.1f * alpha);
        } else if (invulnerable) {
            sprite.draw(batch, dead_alpha * alpha);
            dead_alpha += 0.05;
            if (dead_alpha > 1) dead_alpha = 0;
        } else {
            sprite.draw(batch, alpha);
        }
    }
}
