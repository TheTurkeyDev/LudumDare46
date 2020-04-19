package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

import java.util.ArrayList;
import java.util.List;

public class DeadTree extends Tree
{
	public DeadTree(World world, Vector2 location)
	{
		super("dead", world, location);
	}

	@Override
	public void update()
	{

	}

	@Override
	public List<ItemStack> getDrops()
	{
		List<ItemStack> drops = new ArrayList<>();
		drops.add(ItemRegistry.getItemStack("stick", 3));
		drops.add(ItemRegistry.getItemStack("log", 1));
		return drops;
	}

	@Override
	public void trim()
	{
		LD46.SNACK_BAR.createSnackMessage("YOU CANNOT TRIM THIS TREE!", Color.RED);
	}
}
