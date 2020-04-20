package com.theprogrammingturkey.ld46.item;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;

public class WateringCan extends Item
{
	public WateringCan(String id)
	{
		super(id);
	}

	public boolean isUseable()
	{
		return true;
	}

	public void onUse(World world, Vector2 vec)
	{

	}
}
