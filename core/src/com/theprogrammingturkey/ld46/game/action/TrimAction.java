package com.theprogrammingturkey.ld46.game.action;

import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.Player;

public class TrimAction extends Action
{
	public TrimAction(Plant plant, Player player)
	{
		super("Trim", () ->
		{
			player.getInventory().addToInventory(plant.getSaplingItem(), 1);
			plant.trim();
		});
	}
}
