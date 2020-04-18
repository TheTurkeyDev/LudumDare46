package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.rendering.Renderer;

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

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		Renderer.drawString(Renderer.font, x + 25, y, "LIFE POINTS: " + getCurrentValue(), 1f, Color.BLACK);
	}
}
