package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;
import com.theprogrammingturkey.ld46.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class Entity
{
	protected World world;
	protected Vector2 location;
	protected Vector2 size;

	private boolean dead = false;

	protected List<WrapperTR> regions = new ArrayList<>();

	public Entity(World world, Vector2 location, Vector2 size)
	{
		this.world = world;
		this.location = location;
		this.size = size;
	}

	public void addTexture(WrapperTR wrapperTR)
	{
		regions.add(wrapperTR);
	}

	public void update()
	{

	}

	public void render(float delta)
	{
		for(WrapperTR region : regions)
		{
			if(region.hasTint())
			{
				Renderer.draw(region.getRegion(), location.x, location.y, size.x, size.y, region.getTint());
			}
			else
			{
				Renderer.draw(region.getRegion(), location.x, location.y, size.x, size.y);
			}
		}
	}

	public Vector2 getLocation()
	{
		return this.location;
	}

	public Vector2 getSize()
	{
		return this.size;
	}

	public Rectangle getBoundingBox()
	{
		return new Rectangle(location.x, location.y, size.x, size.y);
	}

	public void kill()
	{
		this.dead = true;
	}

	public void remove()
	{
		this.dead = true;
	}

	public boolean isDead()
	{
		return dead;
	}

	public void onClick(GameScreen screen, Player player)
	{

	}
}
