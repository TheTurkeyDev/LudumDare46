package com.theprogrammingturkey.ld46;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.rendering.Renderer;

public class LD46 extends Game
{
	private static final Color SKY_BLUE = Color.valueOf("87CEEB");

	public static int width = 1280;
	public static int height = 720;

	@Override
	public void create()
	{
		Renderer.init();
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
	}
}
