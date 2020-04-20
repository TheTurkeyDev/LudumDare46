package com.theprogrammingturkey.ld46.screen.overlay.widgets;

import com.theprogrammingturkey.ld46.sounds.SoundManager;

public class Button extends Widget
{
	public Button(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	public void onClick()
	{
		super.onClick();
		SoundManager.playSound(SoundManager.click);
	}
}
