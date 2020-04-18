package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;

public class LifePointsAttribute extends Attribute
{

	public LifePointsAttribute(float currentValue, float maxValue)
	{
		super(currentValue, 0, maxValue);
	}

	@Override
	public void update(Plant plant)
	{

	}
}
