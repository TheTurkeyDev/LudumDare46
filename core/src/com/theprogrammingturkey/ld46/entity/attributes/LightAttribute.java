package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class LightAttribute extends Attribute
{
	private float baseDecramentValue = .1f;

	public LightAttribute(float currentValue, float minValue, float maxValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant, Weather weather)
	{
		this.currentValue += (plant.getCurrentLightValue() - baseDecramentValue);
		this.currentValue = MathUtils.clamp(this.currentValue, getMinValue(), getMaxValue());
	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		Renderer.draw(Textures.sunOutline, x - 15, y - 28, 32, 32);
		Renderer.draw(Textures.sun, x - 15, y - 28, 32, 32);
		Renderer.drawString(Renderer.font, x + 25, y, "LIGHT: ", 1f, Color.BLACK);
		Renderer.drawString(Renderer.font, x + 200, y, StringUtil.formatDecimal(getCurrentValue()), 1f, Color.BLACK);
	}
}
