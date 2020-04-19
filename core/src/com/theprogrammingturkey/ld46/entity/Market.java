package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class Market extends Entity
{
	public Market(GameCore game, Vector2 location)
	{
		super(game, location, new Vector2(64, 64), new WrapperTR(29 * 64, 0));
	}
}
