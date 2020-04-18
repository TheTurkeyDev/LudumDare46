package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.rendering.Renderer;

public class Entity
{
	protected Vector2 location;
	protected Vector2 size;

	public Entity(Vector2 location, Vector2 size)
	{
		this.location = location;
		this.size = size;
	}

	public void update()
	{

	}

	public void render(float delta)
	{
		Renderer.drawRect(location.x, location.y, size.x, size.y, Color.RED, true);
	}

	public Vector2 getLocation()
	{
		return this.location;
	}
}
