package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.MathHelper;

public class TempratureAttribute extends Attribute
{
	private static final int LOWER_BOUND = -100;
	private static final int UPPER_BOUND = 100;

	public TempratureAttribute(float currentValue, float minValue, float maxValue)
	{
		super(currentValue, minValue, maxValue);
	}

	@Override
	public void update(Plant plant, Weather weather)
	{
		this.currentValue = weather.getTemp();
		float threeFourths = getMinValue() + ((getMaxValue() - getMinValue()) * (3f / 4f));
		if(this.currentValue > threeFourths)
		{
			float factor = 1 + ((this.currentValue - threeFourths) / 20);
			plant.getWaterAttribute().removeViaMultiplier(factor * factor);
		}
	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		int barWidth = 256;
		Renderer.draw(Textures.thermometer, x - 15, y - 32, 32, 32);
		Renderer.drawGradientRect(x + 25, y - 32, barWidth, 32, 0, Color.BLUE, Color.RED, true);
		float tempX = MathHelper.convertValRange(getCurrentValue(), LOWER_BOUND, UPPER_BOUND, 0, 1) * barWidth;
		float lowerX = MathHelper.convertValRange(getMinValue(), LOWER_BOUND, UPPER_BOUND, 0, 1) * barWidth;
		float upperX = MathHelper.convertValRange(getMaxValue(), LOWER_BOUND, UPPER_BOUND, 0, 1) * barWidth;
		Renderer.drawRect(x + 25 + lowerX, y - 36, 3, 40, 0, Color.GREEN, true);
		Renderer.drawRect(x + 25 + upperX, y - 36, 3, 40, 0, Color.GREEN, true);
		Renderer.drawRect(x + 25 + tempX, y - 36, 3, 40, 0, Color.WHITE, true);
	}
}
