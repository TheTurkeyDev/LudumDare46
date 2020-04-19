package com.theprogrammingturkey.ld46.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class Tree extends Plant
{
	public Tree(GameCore gameCore, Vector2 location, WrapperTR... atlasLocations)
	{
		super(gameCore, location, atlasLocations);
	}
}
