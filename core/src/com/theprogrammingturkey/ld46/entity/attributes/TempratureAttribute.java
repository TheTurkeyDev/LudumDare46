package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.rendering.Renderer;

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

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		int barWidth = 256;
		Renderer.drawGradientRect(x + 25, y - 32, barWidth, 32, 0, Color.BLUE, Color.RED, true);
		int tempX = (int) ((getCurrentValue() / (getMaxValue() - getMinValue())) * barWidth);
		Renderer.drawRect(x + 25 + tempX, y - 36, 3, 40, 0, Color.WHITE, true);
	}
}
