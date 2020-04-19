package com.theprogrammingturkey.ld46.inventory;

import com.theprogrammingturkey.ld46.item.Item;

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

	public boolean addToInventory(Item item, int count)
	{
		for(Slot s : slots)
		{
			if(s.addItemToSlot(item, count))
				return true;
		}
		return false;
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
