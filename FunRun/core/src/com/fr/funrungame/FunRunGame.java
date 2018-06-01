package com.fr.funrungame;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Game;
import com.fr.funrungame.view.Screens.MainMenu;

/**
 * The Game's main class.
 */
public class FunRunGame extends Game {

	/**
	 * The Game's Sprite Batch.
	 */
	private SpriteBatch batch;

	/**
	 * The Game's Asset Manager.
	 */
	private AssetManager assetManager;

	/**
	 * The Game Music.
	 */
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

	/**
	 * Loads the assets needed by all screens.
	 */
	private void loadAssets() {
		loadMainMenuAssets();
		loadMapSelectAssets();
		loadPlayerAssets();
		loadPowerUpsAssets();
		loadInGameAssets();

		assetManager.finishLoading();
	}

	/**
	 * Loads the assets needed by Main Menu Screen.
	 */
	private void loadMainMenuAssets(){
		assetManager.load("background_menu.png", Texture.class);
		assetManager.load("title.png", Texture.class);
		assetManager.load("play_button.png", Texture.class);
		assetManager.load("play_button_pressed.png", Texture.class);
		assetManager.load("exit_button.png", Texture.class);
		assetManager.load("exit_button_pressed.png", Texture.class);
		assetManager.load("select_map_button.png", Texture.class);
		assetManager.load("select_map_button_pressed.png", Texture.class);
	}

	/**
	 * Loads the Player assets.
	 */
	private void loadPlayerAssets(){
		assetManager.load("player.png", Texture.class);
		assetManager.load("player_running.png", Texture.class);
		assetManager.load("player_jumping.png", Texture.class);
		assetManager.load("player_falling.png", Texture.class);
		assetManager.load("player_shielded.png", Texture.class);
		assetManager.load("player_running_shielded.png", Texture.class);
		assetManager.load("player_jumping_shielded.png", Texture.class);
		assetManager.load("player_falling_shielded.png", Texture.class);
	}

	/**
	 * Loads the assets needed by Map Select Screen.
	 */
	private void loadMapSelectAssets(){
		assetManager.load("mapselector.png", Texture.class);
		assetManager.load("return.png", Texture.class);
		assetManager.load("MapSelectButtons/map1_button_pressed.png", Texture.class);
		assetManager.load("MapSelectButtons/map2_button_pressed.png", Texture.class);
		assetManager.load("MapSelectButtons/map3_button_pressed.png", Texture.class);
		assetManager.load("MapSelectButtons/map4_button_pressed.png", Texture.class);
		assetManager.load("MapSelectButtons/map1_button.png", Texture.class);
		assetManager.load("MapSelectButtons/map2_button.png", Texture.class);
		assetManager.load("MapSelectButtons/map3_button.png", Texture.class);
		assetManager.load("MapSelectButtons/map4_button.png", Texture.class);
	}

	/**
	 * Loads the Power Ups assets.
	 */
	private void loadPowerUpsAssets(){
		assetManager.load("rocket.png", Texture.class);
		assetManager.load("speed.png", Texture.class);
		assetManager.load("shield.png", Texture.class);
		assetManager.load("noPowerUp.png", Texture.class);
	}

	/**
	 * Loads the assets needed by GameView, PauseMenu and FinishMenu Screens.
	 */
	private void loadInGameAssets(){
		assetManager.load("sounds/Electronic Super Joy - 04 - Darkas.mp3", Music.class);
		assetManager.load("1.png", Texture.class);
		assetManager.load("2.png", Texture.class);
		assetManager.load("3.png", Texture.class);
		assetManager.load("arrow_up.png", Texture.class);
		assetManager.load("arrow_down.png", Texture.class);
		assetManager.load("pause.png", Texture.class);
		assetManager.load("pause_menu.png", Texture.class);
		assetManager.load("leave_button.png", Texture.class);
		assetManager.load("stay_button.png", Texture.class);
		assetManager.load("game_over.png", Texture.class);
		assetManager.load("mainmenu_button.png", Texture.class);
	}

	/**
	 * Starts the game.
	 */
	private void startGame() {
		setScreen(new MainMenu(this));
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
