package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;

public abstract class DectramentalAttribute extends Attribute
{
	float decramentValue;

	public DectramentalAttribute(float currentValue, float minValue, float maxValue, float decramentValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant)
	{

	}
}
