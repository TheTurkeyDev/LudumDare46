package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;

public class WaterAttribute extends DectramentalAttribute
{
	public WaterAttribute(float currentValue, float minValue, float maxValue, float decramentValue)
	{
		super(currentValue, minValue, maxValue, decramentValue);
	}

	@Override
	public void update(Plant plant)
	{

	}
}
