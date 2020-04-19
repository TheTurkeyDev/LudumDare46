package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
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
		drops.add(ItemRegistry.getItemStack("stick", (int) getSize().x / 20));
		drops.add(ItemRegistry.getItemStack("log", (int) getSize().x / 30));
		drops.add(ItemRegistry.getItemStack(id, (int) getSize().x / 75));
		return drops;
	}

	@Override
	public void kill()
	{
		super.kill();
		LD46.SNACK_BAR.createSnackMessage("SEEMS THE TREE DIDN'T SURVIVE THE TRIM", Color.RED);
		PlantFactory plantFactory = new PlantFactory(PlantType.TREE, "dead");
		Plant p = plantFactory.create(world, location.cpy(), 0);
		p.setSize(96);
		world.spawnPlant(p);
	}
}
