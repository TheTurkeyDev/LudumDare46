package com.theprogrammingturkey.ld46.inventory;

import com.theprogrammingturkey.ld46.item.ItemStack;

public class Slot
{
	private ItemStack stack = ItemStack.EMPTY;

	public Slot()
	{

	}

	public boolean addItemToSlot(ItemStack stack)
	{
		if(this.stack == ItemStack.EMPTY)
		{
			this.stack = stack;
			return true;
		}
		else if(this.stack.equals(stack))
		{
			this.stack.inc(stack.getAmount());
			stack.setAmount(0);
			return true;
		}
		return false;
	}

	public ItemStack removeFromSlot(int count)
	{
		ItemStack removed = ItemStack.EMPTY;
		if(this.stack != ItemStack.EMPTY)
		{
			removed = new ItemStack(this.stack.getItem());
			int removeAmt = Math.min(this.stack.getAmount(), count);
			removed.setAmount(removeAmt);
			this.stack.dec(removeAmt);
			if(this.stack.getAmount() == 0)
				this.stack = ItemStack.EMPTY;
		}
		return removed;
	}

	public void decrementSlotCount()
	{
		if(this.stack != ItemStack.EMPTY && this.stack.getAmount() > 0)
		{
			this.stack.dec(1);
			if(this.stack.getAmount() == 0)
				this.stack = ItemStack.EMPTY;
		}
	}

	public ItemStack getStack()
	{
		return stack;
	}

	@Override
	public String toString()
	{
		return stack.toString();
	}
}
