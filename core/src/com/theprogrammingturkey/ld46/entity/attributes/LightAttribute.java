package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.rendering.Renderer;

public class LightAttribute extends Attribute
{
	public LightAttribute(float currentValue, float minValue, float maxValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant)
	{

	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{

		Renderer.drawCircle(x, y - 13, 20, Color.YELLOW, true);
		Renderer.drawString(Renderer.font, x + 25, y, "LIGHT: ", 1f, Color.BLACK);
		Renderer.drawString(Renderer.font, x + 200, y, String.valueOf(getCurrentValue()), 1f, Color.BLACK);
	}
}
