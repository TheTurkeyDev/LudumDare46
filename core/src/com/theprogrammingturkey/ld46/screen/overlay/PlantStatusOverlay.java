package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.screen.GameScreen;

public class PlantStatusOverlay extends Overlay
{
	public PlantStatusOverlay(GameScreen screen, Overlay parent)
	{
		super(screen, parent);
	}

	public void render(float delta)
	{
		Renderer.drawRect(100, 100, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 200, Color.LIGHT_GRAY, true);
		Renderer.drawString(Renderer.font, (Gdx.graphics.getWidth() / 2f) - 50, Gdx.graphics.getHeight() - 110, "WELCOME", 1f, Color.BLACK);
	}
}
