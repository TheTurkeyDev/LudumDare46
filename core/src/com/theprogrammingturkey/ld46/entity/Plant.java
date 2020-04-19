package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.attributes.Attribute;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.LightAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.NutrientAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.TempratureAttribute;
import com.theprogrammingturkey.ld46.entity.attributes.WaterAttribute;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Entity
{
	private LifePointsAttribute lifePointsAttribute;
	private LightAttribute lightAttribute;
	private TempratureAttribute tempratureAttribute;
	private WaterAttribute waterAttribute;
	private List<NutrientAttribute> nutrientAttributes = new ArrayList<>();

	private String displayName = "";

	private float growthRate = 0.0005f;

	private float currentLightValue = 0;

	public Plant(GameCore gameCore, Vector2 location, WrapperTR... atlasLocations)
	{
		super(gameCore, location, new Vector2(32, 32), atlasLocations);
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

		currentLightValue = game.getCurrentLight();

		lifePointsAttribute.update(this, game.getWeather());
		lightAttribute.update(this, game.getWeather());
		tempratureAttribute.update(this, game.getWeather());
		waterAttribute.update(this, game.getWeather());
		for(NutrientAttribute attribute : nutrientAttributes)
		{
			attribute.update(this, game.getWeather());
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

	public void setSize(float size)
	{
		this.size.set(size, size);
	}

	public LifePointsAttribute getLifePointsAttribute()
	{
		return lifePointsAttribute;
	}

	public void setLifePointsAttribute(LifePointsAttribute lifePointsAttribute)
	{
		this.lifePointsAttribute = lifePointsAttribute;
	}

	public LightAttribute getLightAttribute()
	{
		return lightAttribute;
	}

	public void setLightAttribute(LightAttribute lightAttribute)
	{
		this.lightAttribute = lightAttribute;
	}

	public TempratureAttribute getTempratureAttribute()
	{
		return tempratureAttribute;
	}

	public void setTempratureAttribute(TempratureAttribute tempratureAttribute)
	{
		this.tempratureAttribute = tempratureAttribute;
	}

	public WaterAttribute getWaterAttribute()
	{
		return waterAttribute;
	}

	public void setWaterAttribute(WaterAttribute waterAttribute)
	{
		this.waterAttribute = waterAttribute;
	}

	public List<NutrientAttribute> getNutrientAttributes()
	{
		return nutrientAttributes;
	}

	public void addNutrientAttributes(NutrientAttribute nutrientAttribute)
	{
		this.nutrientAttributes.add(nutrientAttribute);
	}

	@Override
	public Rectangle getBoundingBox()
	{
		return new Rectangle(location.x - (size.x / 2), location.y, size.x, size.y);
	}

	public float getCurrentLightValue()
	{
		return this.currentLightValue;
	}
}
