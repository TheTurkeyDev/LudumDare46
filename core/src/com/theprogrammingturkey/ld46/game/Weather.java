package com.theprogrammingturkey.ld46.game;

public class Weather
{
	public float temp = 0;
	public float low = 2;
	public float high = 11;

	public void update(Time time)
	{
		int hour = time.getHour();
		if(hour < 12)
			temp = ((high - low) * (2 * time.getDayPercent())) + low;
		else
			temp = ((high - low) * (2 * (1 - time.getDayPercent()))) + low;
	}

	public float getTemp()
	{
		return temp;
	}
}
