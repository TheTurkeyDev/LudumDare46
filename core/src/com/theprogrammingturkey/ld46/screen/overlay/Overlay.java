package com.theprogrammingturkey.ld46.screen.overlay;

import com.badlogic.gdx.Gdx;
import com.theprogrammingturkey.ld46.screen.GameScreen;
import com.theprogrammingturkey.ld46.screen.overlay.widgets.Widget;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Overlay
{
	private Overlay parent;
	protected GameScreen screen;

	private List<Widget> widgets = new ArrayList<>();

	public Overlay(GameScreen screen, Overlay parent)
	{
		this.screen = screen;
		this.parent = parent;
	}

	public void addWidget(Widget widget)
	{
		widgets.add(widget);
	}

	public void render(float delta)
	{
		for(Widget widget : widgets)
		{
			widget.render(delta);
		}
	}

	public void close()
	{
		screen.setCurrentOverlay(parent);
	}

	public boolean keyDown(int keycode)
	{
		return false;
	}

	public boolean keyUp(int keycode)
	{
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		int yFlip = Gdx.graphics.getHeight() - screenY;
		Rectangle rect = new Rectangle();
		for(Widget widget : widgets)
		{
			rect.setBounds(widget.getX(), widget.getY(), widget.getWidth(), widget.getHeight());
			if(rect.contains(screenX, yFlip))
			{
				widget.onClick();
			}
		}
		return false;
	}
}
