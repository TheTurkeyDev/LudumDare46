package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class OakTree extends Tree
{
	public OakTree(GameCore gameCore, Vector2 location)
	{
		super(gameCore, location, new WrapperTR(128, 0), new WrapperTR(192, 0, Color.GREEN));
	}
}
