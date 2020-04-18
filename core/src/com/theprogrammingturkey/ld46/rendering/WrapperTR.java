package com.theprogrammingturkey.ld46.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

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
		this(x, y, null);
	}

	public WrapperTR(int x, int y, Color tint)
	{
		this.x = x;
		this.y = y;
		this.width = 64;
		this.height = 64;
		this.tint = tint;
		this.region = new TextureRegion(Renderer.atlas, x, y, width, height);
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

	public TextureRegion getRegion()
	{
		return region;
	}
}
