package com.theprogrammingturkey.ld46.inventory;

import com.theprogrammingturkey.ld46.item.Item;
import com.theprogrammingturkey.ld46.item.ItemStack;

public class Inventory
{
	private Slot[] slots;

	public Inventory(int slotCount)
	{
		slots = new Slot[slotCount];
		for(int i = 0; i < slotCount; i++)
		{
			slots[i] = new Slot();
		}
	}

	public boolean addToInventory(ItemStack itemstack)
	{
		for(Slot s : slots)
		{
			if(s.getStack().equals(itemstack))
				if(s.addItemToSlot(itemstack))
					return true;
		}

		for(Slot s : slots)
		{
			if(s.addItemToSlot(itemstack))
				return true;
		}
		return false;
	}

	public Slot getSlotForItem(Item item)
	{
		for(Slot s : slots)
		{
			if(s.getStack().getItem() == item)
				return s;
		}
		return null;
	}

	public Slot getSlot(int index)
	{
		return slots[index];
	}

	public Slot[] getSlots()
	{
		return slots;
	}
}
