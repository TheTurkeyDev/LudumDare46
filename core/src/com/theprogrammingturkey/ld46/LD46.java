package com.theprogrammingturkey.ld46;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.SnackBar;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.screen.GameScreen;

public class LD46 extends Game
{
	private static final Color SKY_BLUE = Color.valueOf("87CEEB");

	public static final SnackBar SNACK_BAR = new SnackBar();

	public static int width = 1280;
	public static int height = 720;

	@Override
	public void create()
	{
		Renderer.init();
		Textures.init();
		ItemRegistry.registerItems();
		super.setScreen(new GameScreen());
	}

	@Override
	public void render()
	{
		//Scheduler.tickSyncTasks();
		Gdx.gl.glClearColor(SKY_BLUE.r, SKY_BLUE.g, SKY_BLUE.b, SKY_BLUE.a);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

		Renderer.begin();
		super.render();
		SNACK_BAR.render();
		Renderer.end();
	}

	@Override
	public void resize(int width, int height)
	{
		Renderer.resize(width, height);
	}

	@Override
	public void dispose()
	{
		Renderer.dispose();
		Textures.dispose();
	}
}
