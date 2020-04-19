package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.GameColors;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entity
{
	protected GameCore game;
	protected Vector2 location;
	protected Vector2 size;

	private boolean dead = false;

	protected List<WrapperTR> regions = new ArrayList<>();

	public Entity(GameCore game, Vector2 location, Vector2 size, WrapperTR... atlasLocs)
	{
		this.game = game;
		this.location = location;
		this.size = size;
		Collections.addAll(regions, atlasLocs);
	}

	public void update()
	{

	}

	public void render(float delta)
	{
		if(getBoundingBox().contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
		{
			Renderer.drawCircle(location.x + (size.x / 2), location.y + (size.y / 2), 64, GameColors.VALID_AREA, true);
			Renderer.drawCircle(location.x + (size.x / 2), location.y + (size.y / 2), 64, GameColors.VALID_AREA_NO_ALPHA, false);
		}

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

	public boolean isDead()
	{
		return dead;
	}
}
