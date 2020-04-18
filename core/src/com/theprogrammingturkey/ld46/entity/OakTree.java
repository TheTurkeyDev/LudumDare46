package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.rendering.Renderer;

public class OakTree extends Tree
{
	public OakTree(Vector2 location)
	{
		super(location);
	}

	public void render(float delta)
	{
		Renderer.drawRect(location.x, location.y, size.x, size.y, Color.GREEN, true);
	}
}
