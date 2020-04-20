package com.theprogrammingturkey.ld46.game.action;

import com.theprogrammingturkey.ld46.entity.Plant;
import com.theprogrammingturkey.ld46.entity.Player;
import com.theprogrammingturkey.ld46.inventory.Slot;
import com.theprogrammingturkey.ld46.item.ItemStack;
import com.theprogrammingturkey.ld46.item.WateringCan;
import com.theprogrammingturkey.ld46.sounds.SoundManager;

public class WateringAction extends Action
{
	public WateringAction(Plant plant, Player player)
	{
		super("Water", () ->
		{
			ItemStack wateringCan = ItemStack.EMPTY;
			for(Slot s : player.getInventory().getSlots())
			{
				if(s.getStack().getItem() instanceof WateringCan)
				{
					wateringCan = s.getStack();
					break;
				}
			}
			if(wateringCan == ItemStack.EMPTY)
				return;

			plant.getWaterAttribute().setCurrentValue(plant.getWaterAttribute().getCurrentValue() + 5);
			SoundManager.playSound(SoundManager.watering);
		});
	}
}
