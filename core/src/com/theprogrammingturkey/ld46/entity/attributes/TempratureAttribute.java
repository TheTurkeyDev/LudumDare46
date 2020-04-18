package com.theprogrammingturkey.ld46.entity.attributes;

import com.theprogrammingturkey.ld46.entity.Plant;

public class TempratureAttribute extends Attribute
{
	public TempratureAttribute(float currentValue, float minValue, float maxValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant)
	{

	}
}
