package com.fr.funrungame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Game;
import com.fr.funrungame.view.Screens.MainMenu;

public class FunRunGame extends Game {
	private SpriteBatch batch;
	private AssetManager assetManager;

	/**
	 * Creates the game. Initializes the sprite batch and asset manager.
	 * Also starts the game until we have a main menu.
	 */
	 @Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadAssets();

		startGame();
	}

	private void loadAssets() {
	 	assetManager.load("background_menu.png", Texture.class);
		assetManager.load("title.png", Texture.class);
		assetManager.load("play_button.png", Texture.class);
		assetManager.load("exit_button.jpg", Texture.class);
		assetManager.load("exit_button.jpg", Texture.class);
		assetManager.load("1.jpg", Texture.class);
		assetManager.load("2.jpg", Texture.class);
		assetManager.load("3.png", Texture.class);


		assetManager.finishLoading();
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
