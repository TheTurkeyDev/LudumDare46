package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class Market extends Entity
{
	public Market(World world, Vector2 location)
	{
		super(world, location, new Vector2(64, 64));
		super.addTexture(new WrapperTR(29 * 64, 0));
	}
}
