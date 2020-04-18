package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;

public abstract class Attribute
{
	private float currentValue;
	private float minValue;
	private float maxValue;

	public Attribute(float currentValue, float minValue, float maxValue)
	{
		this.currentValue = currentValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
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

	public abstract void update(Plant plant);

	public abstract void renderAsInfoGraphic(float delta, int x, int y);
}
