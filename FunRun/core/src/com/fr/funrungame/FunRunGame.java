package com.fr.funrungame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.fr.funrungame.view.Screens.MainMenu;

import java.util.HashMap;
import java.util.Map;

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

//		 Map parameters = new HashMap();
//		 parameters.put("map", "1");
//
//		 Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
//		 httpGet.setUrl("https://paginas.fe.up.pt/~up201605646/lpoo/get.php");
//		 httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));
//
//		 Gdx.net.sendHttpRequest(httpGet, new Net.HttpResponseListener() {
//			 @Override
//			 public void handleHttpResponse(Net.HttpResponse httpResponse) {
//				 System.out.println(httpResponse.getResultAsString());
//			 }
//
//			 @Override
//			 public void failed(Throwable t) {
//				 System.out.println("Failed");
//			 }
//
//			 @Override
//			 public void cancelled() {
//				 System.out.println("Cancelled");
//			 }
//		 });

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
