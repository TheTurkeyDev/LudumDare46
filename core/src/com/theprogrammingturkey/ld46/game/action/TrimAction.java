package com.theprogrammingturkey.ld46.game.action;

import com.badlogic.gdx.graphics.Color;
import com.theprogrammingturkey.ld46.LD46;
import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.sounds.SoundManager;

public class TrimAction extends Action
{
	public TrimAction(Plant plant, Player player)
	{
		super("Trim", () ->
		{
			if(plant.trim())
				player.getInventory().addToInventory(plant.getSaplingItem());
			if(plant.isDead())
				LD46.SNACK_BAR.createSnackMessage("SEEMS THE PLANT DIDN'T SURVIVE THE TRIM", Color.RED);
			SoundManager.playRandomSnip();
		});
	}
}
