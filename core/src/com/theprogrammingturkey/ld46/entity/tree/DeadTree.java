package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.attributes.LifePointsAttribute;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class DeadTree extends Tree
{
	public DeadTree(GameCore gameCore, Vector2 location)
	{
		super(gameCore, location, new WrapperTR(128, 0));
	}

	public void setLifePointsAttribute(LifePointsAttribute lp)
	{
		super.setLifePointsAttribute(lp);
		setSize(64);
	}

	@Override
	public void update()
	{

	}

	@Override
	public void trim()
	{
		LD46.SNACK_BAR.createSnackMessage("YOU CANNOT TRIM THIS TREE!", Color.RED);
	}
}
