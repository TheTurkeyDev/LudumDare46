package com.theprogrammingturkey.ld46.game;

public class Time
{
	private long gameTime = 360 * 60;
	private static int dayLength = 60 * 24;

	public void update()
	{
		gameTime++;
	}

	public int getHour()
	{
		long currentTime = this.gameTime;
		currentTime = currentTime % (60 * 60 * 60);
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
}
