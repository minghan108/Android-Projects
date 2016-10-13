package com.Ming.MathApp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Ming.MathApp.MathLink;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MathLink.WIDTH;
		config.height = MathLink.HEIGHT;
		config.title = MathLink.TITLE;
		new LwjglApplication(new MathLink(), config);
	}
}
