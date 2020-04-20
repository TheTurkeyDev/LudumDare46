package com.theprogrammingturkey.ld46.screen.overlay.widgets;

public class Widget
{
	private int x;
	private int y;
	private int width;
	private int height;

	private ICallback callback;

	public Widget(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(float delta)
	{

	}

	public void setCallback(ICallback callback)
	{
		this.callback = callback;
	}

	public void onClick()
	{
		if(callback != null)
			callback.onClick();
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public interface ICallback
	{
		void onClick();
	}
}
