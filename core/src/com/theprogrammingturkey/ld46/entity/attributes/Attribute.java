package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.World;

public abstract class Attribute
{
	protected float currentValue;
	private float minValue;
	private float maxValue;
	protected float tolerance;

	public Attribute(float currentValue, float minValue, float maxValue, float tolerance)
	{
		this.currentValue = currentValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.tolerance = tolerance;
	}

	public float getCurrentValue()
	{
		return currentValue;
	}

	public void setCurrentValue(float currentValue)
	{
		this.currentValue = currentValue;
	}

	public float getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(float maxValue)
	{
		this.maxValue = maxValue;
	}

	public float getMinValue()
	{
		return minValue;
	}

	public void setMinValue(float minValue)
	{
		this.minValue = minValue;
	}

	public abstract void update(World world, Plant plant);

	public abstract void renderAsInfoGraphic(float delta, int x, int y);
}
