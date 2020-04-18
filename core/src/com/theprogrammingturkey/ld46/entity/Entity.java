package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entity
{
	protected Vector2 location;
	protected Vector2 size;

	protected List<WrapperTR> regions = new ArrayList<>();

	public Entity(Vector2 location, Vector2 size, WrapperTR... atlasLocs)
	{
		this.location = location;
		this.size = size;
		Collections.addAll(regions, atlasLocs);
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
}
