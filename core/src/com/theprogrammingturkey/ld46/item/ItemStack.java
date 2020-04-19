package com.theprogrammingturkey.ld46.item;

import java.util.Objects;

public class ItemStack
{
	public static final ItemStack EMPTY = new ItemStack(null);

	private Item item;
	private int amount;

	public ItemStack(Item item)
	{
		this(item, 1);
	}

	public ItemStack(Item item, int amount)
	{
		this.item = item;
		this.amount = amount;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public void inc(int amount)
	{
		this.amount += amount;
	}

	public void dec(int amount)
	{
		this.amount -= amount;
	}

	@Override
	public String toString()
	{
		return "ItemStack{" +
				"item=" + item +
				", amount=" + amount +
				'}';
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		ItemStack stack = (ItemStack) o;
		return Objects.equals(item, stack.item);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(item);
	}
}
