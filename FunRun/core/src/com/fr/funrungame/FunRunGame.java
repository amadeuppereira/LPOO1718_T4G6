package com.fr.funrungame;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Game;
import com.fr.funrungame.view.Screens.LoadingScreen;
import com.fr.funrungame.view.Screens.MainMenu;


public class FunRunGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;
	private Music music;

	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 * Also starts the game until we have a main menu.
	 */
	 @Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadAssets();

		music = assetManager.get("sounds/Electronic Super Joy - 04 - Darkas.mp3");
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

		startGame();
	}

	private void loadAssets() {
	 	assetManager.load("background_menu.png", Texture.class);
		assetManager.load("title.png", Texture.class);
		assetManager.load("play_button.png", Texture.class);
		assetManager.load("play_button_pressed.png", Texture.class);
		assetManager.load("exit_button.png", Texture.class);
		assetManager.load("exit_button_pressed.png", Texture.class);
		assetManager.load("customize_button.png", Texture.class);
		assetManager.load("customize_button_pressed.png", Texture.class);
		assetManager.load("mapselector.png", Texture.class);
		assetManager.load("return.png", Texture.class);
		assetManager.load("1.png", Texture.class);
		assetManager.load("2.png", Texture.class);
		assetManager.load("3.png", Texture.class);
		assetManager.load("arrow_up.png", Texture.class);
		assetManager.load("arrow_down.png", Texture.class);
		assetManager.load("sounds/Electronic Super Joy - 04 - Darkas.mp3", Music.class);
		assetManager.load("background_menu.png", Texture.class);
		assetManager.load("player.png", Texture.class);
		assetManager.load("player_running.png", Texture.class);
		assetManager.load("player_jumping.png", Texture.class);
		assetManager.load("player_falling.png", Texture.class);
		assetManager.load("player_shielded.png", Texture.class);
		assetManager.load("player_running_shielded.png", Texture.class);
		assetManager.load("player_jumping_shielded.png", Texture.class);
		assetManager.load("player_falling_shielded.png", Texture.class);
		assetManager.load("rocket.png", Texture.class);
		assetManager.load("speed.png", Texture.class);
		assetManager.load("shield.png", Texture.class);
		assetManager.load("noPowerUp.png", Texture.class);
		assetManager.load("pause.png", Texture.class);
		assetManager.load("pause_menu.png", Texture.class);
		assetManager.load("leave_button.png", Texture.class);
		assetManager.load("stay_button.png", Texture.class);
        assetManager.load("game_over.png", Texture.class);
        assetManager.load("mainmenu_button.png", Texture.class);
		assetManager.load("loading_screen.jpg", Texture.class);

		assetManager.finishLoading();
	}

	/**
	 * Starts the game.
	 */
	private void startGame() {
		setScreen(new LoadingScreen(this));
	}

	/**
	 * Disposes of all assets.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
		music.dispose();
	}

	/**
	 * Returns the asset manager used to load all textures and sounds.
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}
}
