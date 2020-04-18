package com.theprogrammingturkey.ld46.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.theprogrammingturkey.ld46.LD46;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Ludum Dare 46";
		config.resizable = false;
		config.width = LD46.width;
		config.height = LD46.height;
		new LwjglApplication(new LD46(), config);
	}
}
