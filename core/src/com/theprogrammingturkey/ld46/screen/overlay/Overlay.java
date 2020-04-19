package com.theprogrammingturkey.ld46.screen.overlay;

import com.theprogrammingturkey.ld46.screen.GameScreen;

public class Overlay
{
	private Overlay parent;
	private GameScreen screen;

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

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}
}
