package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WrapperTR
{
	private int x;
	private int y;
	private int width;
	private int height;
	private TextureRegion region;
	private Color tint;

	public WrapperTR(int x, int y)
	{
		this(x, y, 64, 64);
	}

	public WrapperTR(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.region = new TextureRegion(Textures.atlas, x, y, width, height);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean hasTint()
	{
		return this.tint != null;
	}

	public Color getTint()
	{
		return tint;
	}

	public void setTint(Color tint)
	{
		this.tint = tint;
	}

	public TextureRegion getRegion()
	{
		return region;
	}
}
