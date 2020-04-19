package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;

public class BonsaiTree extends Tree
{
	public BonsaiTree(World world, Vector2 location)
	{
		super("bonsai", world, location);
	}
}
