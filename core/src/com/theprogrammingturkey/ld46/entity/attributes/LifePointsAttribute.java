package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.GameValues;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class LifePointsAttribute extends Attribute
{

	public LifePointsAttribute(float mexHealth)
	{
		super(mexHealth, 0, mexHealth, 0);
	}

	@Override
	public void update(World world, Plant plant)
	{
		float lpChange = GameValues.LP_GAIN;
		WaterAttribute water = plant.getWaterAttribute();
		//TODO: Tolerance Attribute
		if(water.currentValue < water.getMinValue())
			lpChange -= (water.getMinValue() - water.currentValue) / water.tolerance;
		else if(water.currentValue > water.getMaxValue())
			lpChange -= (water.currentValue - water.getMaxValue()) / water.tolerance;

		LightAttribute light = plant.getLightAttribute();
		if(light.currentValue == 0 || light.currentValue == light.getMaxValue())
		{
			//currentValue -= 0.005;
		}

		TempratureAttribute temp = plant.getTempratureAttribute();
		//TODO: Tolerance Attribute
		if(temp.currentValue < temp.getMinValue())
			lpChange -= (temp.getMinValue() - temp.currentValue) / temp.tolerance;
		else if(temp.currentValue > temp.getMaxValue())
			lpChange -= (temp.currentValue - temp.getMaxValue()) / temp.tolerance;

		lpChange = MathUtils.clamp(lpChange, -.1f, 10);
		currentValue += lpChange;

		currentValue = MathUtils.clamp(currentValue, getMinValue(), getMaxValue());

		if(currentValue <= 0 && !plant.isDead())
			plant.kill();
	}

	public void increaseMaxLP(float amount)
	{
		this.setMaxValue(this.getMaxValue() + amount);
	}

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		String currHealth = StringUtil.formatDecimal(getCurrentValue());
		String maxHealth = StringUtil.formatDecimal(getMaxValue());
		Renderer.drawString(Renderer.font, x + 25, y, "LIFE POINTS: " + currHealth + " / " + maxHealth, 1f, Color.BLACK);
	}
}
