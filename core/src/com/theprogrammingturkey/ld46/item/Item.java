package com.theprogrammingturkey.ld46.item;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

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

	@Override
	public String toString()
	{
		if(displayName == null)
			return "N/A";
		return displayName;
	}
}
