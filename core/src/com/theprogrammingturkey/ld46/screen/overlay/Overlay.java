package com.theprogrammingturkey.ld46.screen.overlay;

import com.theprogrammingturkey.ld46.screen.GameScreen;

public class Overlay
{
	private Overlay parent;
	protected GameScreen screen;

	public Overlay(GameScreen screen, Overlay parent)
	{
		this.screen = screen;
		this.parent = parent;
	}

	public void render(float delta)
	{

	}

	public void close()
	{
		screen.setCurrentOverlay(parent);
	}

	public boolean keyDown(int keycode)
	{
		return false;
	}

	public boolean keyUp(int keycode)
	{
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}
}
