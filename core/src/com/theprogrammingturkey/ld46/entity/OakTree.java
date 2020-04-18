package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class OakTree extends Tree
{
	public OakTree(Vector2 location)
	{
		super(location, new WrapperTR(128, 0), new WrapperTR(192, 0, Color.GREEN));
	}
}
