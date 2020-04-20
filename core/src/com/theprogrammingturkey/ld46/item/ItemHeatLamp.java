package com.theprogrammingturkey.ld46.item;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.HeatLamp;
import com.theprogrammingturkey.ld46.game.World;

public class ItemHeatLamp extends Item
{
	public ItemHeatLamp(String id)
	{
		super(id);
	}

	public boolean isPlaceable()
	{
		return true;
	}

	public void onPlace(World world, Vector2 vec)
	{
		world.spawnEntity(new HeatLamp(world, vec));
	}
}
