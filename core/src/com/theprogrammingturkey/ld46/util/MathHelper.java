package com.theprogrammingturkey.ld46.util;

public class MathHelper
{

	public static float convertValRange(float oldValue, float oldMin, float oldMax, float newMin, float newMax)
	{
		return (((oldValue - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
	}
}
