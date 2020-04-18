package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.attributes.Attribute;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Entity
{
	private List<Attribute> attributes = new ArrayList<>();

	private String displayName = "";

	private float growthRate = 0.0005f;

	public Plant(Vector2 location, WrapperTR... atlasLocations)
	{
		super(location, new Vector2(32, 32), atlasLocations);
	}

	public void setDisplayName(String name)
	{
		this.displayName = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public float getGrowthRate()
	{
		return growthRate;
	}

	public void setGrowthRate(float growthRate)
	{
		this.growthRate = growthRate;
	}

	@Override
	public void update()
	{
		super.update();

		this.size.add(growthRate, growthRate);

		for(Attribute attribute : attributes)
		{
			attribute.update(this);
		}
	}

	public void renderPreview(float delta, float x, float y, float width, float height)
	{
		for(WrapperTR region : regions)
		{
			if(region.hasTint())
			{
				Renderer.draw(region.getRegion(), x, y, width, height, region.getTint());
			}
			else
			{
				Renderer.draw(region.getRegion(), x, y, width, height);
			}

		}
	}

	@Override
	public void render(float delta)
	{
		for(WrapperTR region : regions)
		{
			if(region.hasTint())
			{
				Renderer.draw(region.getRegion(), location.x - (size.x / 2), location.y, size.x, size.y, region.getTint());
			}
			else
			{
				Renderer.draw(region.getRegion(), location.x - (size.x / 2), location.y, size.x, size.y);
			}
		}
	}

	public void addAttribute(Attribute attribute)
	{
		this.attributes.add(attribute);
	}

	public List<Attribute> getAttributes()
	{
		return this.attributes;
	}

	@Override
	public Rectangle getBoundingBox()
	{
		return new Rectangle(location.x - (size.x / 2), location.y, size.x, size.y);
	}
}
