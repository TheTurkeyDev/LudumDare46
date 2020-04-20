package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.MathHelper;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class TempratureAttribute extends Attribute
{
	private static final int LOWER_BOUND = -100;
	private static final int UPPER_BOUND = 100;

	public TempratureAttribute(float currentValue, float minValue, float maxValue, float tolerance)
	{
		super(currentValue, minValue, maxValue, tolerance);
	}

	@Override
	public void update(World world, Plant plant)
	{
		this.currentValue = world.getWeather().getTemp();
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

		Renderer.drawStringAligned(Renderer.rust, x + 25, y + 10, String.valueOf(LOWER_BOUND), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + barWidth + 25, y + 10, String.valueOf(UPPER_BOUND), 0.175f, Align.center, Color.BLACK);
		if(LOWER_BOUND != getMinValue())
			Renderer.drawStringAligned(Renderer.rust, x + lowerX + 25, y + 10, String.valueOf(getMinValue()), 0.175f, Align.center, Color.BLACK);
		if(UPPER_BOUND != getMaxValue())
			Renderer.drawStringAligned(Renderer.rust, x + upperX + 25, y + 10, String.valueOf(getMaxValue()), 0.175f, Align.center, Color.BLACK);
		Renderer.drawStringAligned(Renderer.rust, x + 50 + tempX, y - 15, StringUtil.formatDecimal(getCurrentValue()), 0.175f, Align.center, Color.WHITE);

	}
}
