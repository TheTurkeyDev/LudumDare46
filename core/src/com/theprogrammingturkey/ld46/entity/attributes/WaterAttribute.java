package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.rendering.Textures;
import com.theprogrammingturkey.ld46.util.MathHelper;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class WaterAttribute extends Attribute
{
	private static final int LOWER_BOUND = 0;
	private static final int UPPER_BOUND = 100;

	private float baseDecrament;

	public WaterAttribute(float currentValue, float minValue, float maxValue, float baseDecrament, float tolerance)
	{
		super(currentValue, minValue, maxValue, tolerance);
		this.baseDecrament = baseDecrament;
	}

	@Override
	public void update(World world, Plant plant)
	{
		float decramentValue = baseDecrament;
		//TODO: affected by heat
		currentValue -= decramentValue;
		int tileType = world.getTileTypeAtPos(plant.getLocation().x, plant.getLocation().y);
		if(tileType == 0)
			currentValue += 0.001;
		else if(tileType == 1)
			currentValue += 1;

		currentValue = MathUtils.clamp(currentValue, LOWER_BOUND, UPPER_BOUND);
	}

	public void removeViaMultiplier(float multiplier)
	{
		currentValue -= (baseDecrament * multiplier);
	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		int barWidth = 256;
		Renderer.draw(Textures.waterDroplet, x - 15, y - 32, 32, 32);
		Renderer.drawGradientRect(x + 25, y - 32, barWidth, 32, 0, Color.CYAN, Color.BLUE, true);
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
