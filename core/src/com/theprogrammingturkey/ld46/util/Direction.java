package com.theprogrammingturkey.ld46.util;

import com.badlogic.gdx.math.Vector2;

public enum Direction
{
	UP(0),
	LEFT(1),
	DOWN(2),
	RIGHT(3);

	private int val;

	Direction(int val)
	{
		this.val = val;
	}

	public int getVal()
	{
		return val;
	}

	public void addToLoc(Vector2 vec, float amount)
	{
		switch(this)
		{
			case UP:
				vec.add(0, amount);
				break;
			case LEFT:
				vec.add(-amount, 0);
				break;
			case DOWN:
				vec.add(0, -amount);
				break;
			case RIGHT:
				vec.add(amount, 0);
				break;
		}
	}
}
