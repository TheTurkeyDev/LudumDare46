package com.theprogrammingturkey.ld46.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.theprogrammingturkey.ld46.game.World;

public class Item
{
	private String id;
	private String displayName;

	private TextureRegion textureRegion;

	public Item(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public TextureRegion getTextureRegion()
	{
		return textureRegion;
	}

	public void setTextureRegion(TextureRegion textureRegion)
	{
		this.textureRegion = textureRegion;
	}

	public boolean isPlaceable()
	{
		return false;
	}

	public void onPlace(World world, Vector2 vec)
	{

	}

	public boolean isUseable()
	{
		return false;
	}

	public void onUse(World world, Vector2 vec)
	{

	}

	@Override
	public String toString()
	{
		if(displayName == null)
			return "EMPTY";
		return displayName;
	}
}
