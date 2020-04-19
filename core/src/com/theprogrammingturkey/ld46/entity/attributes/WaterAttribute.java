package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class WaterAttribute extends Attribute
{
	private float baseDecrament;

	public WaterAttribute(float currentValue, float minValue, float maxValue, float baseDecrament)
	{
		super(currentValue, minValue, maxValue);
		this.baseDecrament = baseDecrament;
	}

	@Override
	public void update(Plant plant, Weather weather)
	{
		float decramentValue = baseDecrament;
		//TODO: affected by heat
		currentValue -= decramentValue;
	}

	public void removeViaMultiplier(float multiplier)
	{
		currentValue -= (baseDecrament * multiplier);
	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		Renderer.draw(Textures.waterDropletOutline, x - 15, y - 28, 32, 32);
		Renderer.draw(Textures.waterDroplet, x - 15, y - 28, 32, 32);
		Renderer.drawString(Renderer.font, x + 25, y, "WATER: ", 1f, Color.BLACK);
		Renderer.drawString(Renderer.font, x + 200, y, StringUtil.formatDecimal(getCurrentValue()), 1f, Color.BLACK);
	}
}
