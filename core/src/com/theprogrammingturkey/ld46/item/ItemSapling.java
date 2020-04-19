package com.theprogrammingturkey.ld46.item;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.registry.PlantFactory;

public class ItemSapling extends Item
{
	public ItemSapling(String id)
	{
		super(id);
	}

	@Override
	public boolean isPlaceable()
	{
		return true;
	}

	public void onPlace(World world, Vector2 vec)
	{
		PlantFactory factory = new PlantFactory(PlantType.TREE, getId());
		Plant p = factory.create(world, vec, 10);
		world.spawnPlant(p);
	}
}
