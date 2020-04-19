package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.GameValues;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class LifePointsAttribute extends Attribute
{

	public LifePointsAttribute(float mexHealth)
	{
		super(mexHealth, 0, mexHealth);
	}

	@Override
	public void update(Plant plant, Weather weather)
	{
		currentValue += GameValues.LP_GAIN;
		WaterAttribute water = plant.getWaterAttribute();
		if(water.currentValue < (water.getMaxValue() - water.getMinValue()) / 4)
		{
			currentValue -= ((water.getMaxValue() - water.getMinValue()) / 4) - water.currentValue;
		}

		LightAttribute light = plant.getLightAttribute();
		if(light.currentValue == 0 || light.currentValue == light.getMaxValue())
		{
			//currentValue -= 0.005;
		}

		TempratureAttribute temp = plant.getTempratureAttribute();
		if(temp.currentValue < temp.getMinValue() || temp.currentValue > temp.getMaxValue())
		{
			currentValue -= 0.01;
		}
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
