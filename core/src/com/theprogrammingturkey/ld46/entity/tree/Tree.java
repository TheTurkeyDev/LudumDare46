package com.theprogrammingturkey.ld46.entity.tree;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.PlantType;
import com.theprogrammingturkey.ld46.game.GameCore;
import com.theprogrammingturkey.ld46.registry.PlantFactory;
import com.theprogrammingturkey.ld46.rendering.WrapperTR;

public class Tree extends Plant
{
	public Tree(GameCore gameCore, Vector2 location, WrapperTR... atlasLocations)
	{
		super(gameCore, location, atlasLocations);
	}

	@Override
	public void kill()
	{
		super.kill();
		LD46.SNACK_BAR.createSnackMessage("SEEMS THE TREE DIDN'T SURVIVE THE TRIM", Color.RED);
		PlantFactory plantFactory = new PlantFactory(PlantType.TREE, "dead");
		Plant p = plantFactory.create(game, location.cpy());
		game.spawnPlant(p);
	}
}
