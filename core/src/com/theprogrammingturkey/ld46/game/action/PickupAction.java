package com.theprogrammingturkey.ld46.game.action;

import com.theprogrammingturkey.ld46.entity.Entity;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.item.ItemStack;

public class PickupAction extends Action
{
	public PickupAction(Entity entity, Player player, ItemStack stack)
	{
		super("Pickup", () ->
		{
			player.getInventory().addToInventory(stack);
			entity.remove();
		});
	}
}
