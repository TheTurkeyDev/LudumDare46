package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.registry.PlantFactory;

import java.util.List;

public class Tree extends Plant
{
	private String id;

	public Tree(String id, World world, Vector2 location)
	{
		super(world, location);
		this.id = id;
	}

	public ItemStack getSaplingItem()
	{
		return ItemRegistry.getItemStack(id);
	}

	@Override
	public List<ItemStack> getDrops()
	{
		List<ItemStack> drops = super.getDrops();

		int sticks = (int) getSize().x / 20;
		if(sticks > 0)
			drops.add(ItemRegistry.getItemStack("stick", sticks));

		int logs = (int) getSize().x / 30;
		if(logs > 0)
			drops.add(ItemRegistry.getItemStack("log", logs));

		int saplings = (int) getSize().x / 75;
		if(saplings > 0)
			drops.add(ItemRegistry.getItemStack(id, saplings));
		return drops;
	}

	@Override
	public void kill()
	{
		if(isDead())
			return;
		super.kill();
		PlantFactory plantFactory = new PlantFactory(PlantType.TREE, "dead");
		Plant p = plantFactory.create(world, location.cpy(), 1);
		p.setSize(96);
		world.spawnPlant(p);
	}
}
