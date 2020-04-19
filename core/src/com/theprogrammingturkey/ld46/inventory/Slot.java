package com.theprogrammingturkey.ld46.inventory;

import com.theprogrammingturkey.ld46.item.Item;
import com.theprogrammingturkey.ld46.registry.ItemRegistry;

public class Slot
{
	private Item item = ItemRegistry.EMPTY;
	private int amount = 0;

	public Slot()
	{

	}

	public boolean addItemToSlot(Item item, int count)
	{
		if(this.item == ItemRegistry.EMPTY)
		{
			this.item = item;
			amount = count;
			return true;
		}
		else if(this.item == item)
		{
			amount += count;
			return true;
		}
		return false;
	}

	public int removeFromSlot(Item item, int count)
	{
		if(this.item == item && amount > 0)
		{
			amount -= Math.max(amount, count);
			if(amount == 0)
				this.item = ItemRegistry.EMPTY;
			return Math.max(amount, count);
		}
		return count;
	}

	public Item getItem()
	{
		return item;
	}

	public int getAmount()
	{
		return amount;
	}

	@Override
	public String toString()
	{
		return item.toString() + "/" + amount;
	}
}
