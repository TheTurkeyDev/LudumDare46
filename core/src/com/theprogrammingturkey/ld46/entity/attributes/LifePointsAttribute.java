package com.theprogrammingturkey.ld46.entity.attributes;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.game.Weather;
import com.theprogrammingturkey.ld46.rendering.Renderer;
import com.theprogrammingturkey.ld46.util.StringUtil;

public class LifePointsAttribute extends Attribute
{

	public LifePointsAttribute()
	{
		super(100, 0, 100);
	}

	@Override
	public void update(Plant plant, Weather weather)
	{
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

	public void renderAsInfoGraphic(float delta, int x, int y)
	{
		Renderer.drawString(Renderer.font, x + 25, y, "LIFE POINTS: " + StringUtil.formatDecimal(getCurrentValue()), 1f, Color.BLACK);
	}
}
