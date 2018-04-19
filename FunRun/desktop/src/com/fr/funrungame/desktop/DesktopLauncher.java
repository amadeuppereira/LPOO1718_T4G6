package com.fr.funrungame.desktop;

		import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
		import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
		import com.fr.funrungame.FunRunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Fun Run";
		config.useGL30 = false;
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new FunRunGame(), config);
	}
}
