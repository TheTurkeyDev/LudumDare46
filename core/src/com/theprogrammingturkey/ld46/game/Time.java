package com.theprogrammingturkey.ld46.game;

public class Time
{
	private long gameTime = 360 * 60;
	private static int dayLength = 60 * 60 * 24;

	public void update()
	{
		gameTime += 5;
	}

	public int getDay()
	{
		long currentTime = this.gameTime;
		return (int) (currentTime / (60 * 60 * 24));
	}

	public int getHour()
	{
		long currentTime = this.gameTime;
		currentTime = currentTime % (60 * 60 * 24);
		return (int) (currentTime / (60 * 60));
	}

	public int getMinute()
	{
		long currentTime = this.gameTime;
		currentTime = currentTime % (60 * 60);
		return (int) (currentTime / 60);
	}

	public int getSeconds()
	{
		return (int) (this.gameTime % 60);
	}

	public float getDayPercent()
	{
		return (this.gameTime % (60f * 60f * 24f)) / dayLength;
	}
}
