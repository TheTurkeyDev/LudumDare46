package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.rendering.Renderer;

public class NutrientAttribute extends Attribute
{
	public NutrientAttribute(float currentValue, float minValue, float maxValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant)
	{

	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		Renderer.drawString(Renderer.font, x + 25, y, "NUTRIENT LEVEL: " + getCurrentValue(), 1f, Color.BLACK);
	}
}
